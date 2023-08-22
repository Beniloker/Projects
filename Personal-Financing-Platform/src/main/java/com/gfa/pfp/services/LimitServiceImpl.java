package com.gfa.pfp.services;

import com.gfa.pfp.exception.NothingInsideException;
import com.gfa.pfp.handler.limit.LimitHandler;
import com.gfa.pfp.models.dto.MessageDTO;
import com.gfa.pfp.models.dto.limit.LimitDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseListDTO;
import com.gfa.pfp.models.entities.finance.Limit;
import com.gfa.pfp.models.entities.user.User;
import com.gfa.pfp.repositories.LimitRepository;
import com.gfa.pfp.repositories.UserRepository;
import com.gfa.pfp.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final LimitHandler limitHandler;

    @Override
    public LimitResponseDTO setLimit(LimitDTO limitDTO, String request) throws NothingInsideException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));

        if ((Objects.isNull(limitDTO.getCategory()))
                || limitDTO.getCategory().trim().equals("")) {
            limitDTO.setCategory("other");
        }
        if (limitDTO.getExpenseLimit() == null) {
            limitDTO.setExpenseLimit(1000.0);
        }
        if (limitDTO.getStart() == null) {
            limitDTO.setStart(LocalDate.now());
        }
        if (limitDTO.getEnd() == null || limitDTO.getEnd().isBefore(limitDTO.getStart())) {
            limitDTO.setEnd(limitDTO.getStart().plusMonths(1));
        }

        if (limitHandler.getCategoryByName(limitDTO, user.getId()).isEmpty()) {
            throw new NothingInsideException("Category '" + limitDTO.getCategory() + "' not found");
        }
        if (limitHandler.getLimitByCategoryName(limitDTO, user).isPresent()) {
            Limit existingLimit = limitHandler.getLimitByCategoryName(limitDTO, user).get();
            existingLimit.setExpenseLimit(limitDTO.getExpenseLimit());
            existingLimit.setStart(limitDTO.getStart());
            existingLimit.setEnd(limitDTO.getEnd());
            limitRepository.save(existingLimit);
        } else {
            Limit newLimit = new Limit(
                    limitHandler.getCategoryByName(limitDTO, user.getId()).get(),
                    limitDTO.getExpenseLimit(),
                    limitDTO.getStart(),
                    limitDTO.getEnd(),
                    user);
            limitRepository.save(newLimit);
        }
        return limitHandler.getLimitResponseDTO(limitDTO, user);
    }

    @Override
    public LimitResponseListDTO checkAllLimits(String request) throws NothingInsideException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        if (limitRepository.findAllByUser(user).isEmpty()) {
            throw new NothingInsideException("You don't have any limit");
        }
        List<Limit> limitList = limitRepository.findAllByUser(user);
        HashMap<Long, LimitResponseDTO> limits = new HashMap<>();
        for (int i = 0; i < limitList.size(); i++) {
            LimitDTO getDTO = LimitDTO
                    .builder()
                    .category(limitList.get(i).getCategory().getName())
                    .expenseLimit(limitList.get(i).getExpenseLimit())
                    .start(limitList.get(i).getStart())
                    .end(limitList.get(i).getEnd())
                    .build();
            limits.put(limitRepository.findAllByUser(user).get(i).getId(),
                    limitHandler.getLimitResponseDTO(getDTO, user));
        }
        return limits.isEmpty() ? null : LimitResponseListDTO.builder().limits(limits).build();
    }

    @Override
    public LimitResponseDTO checkLimitByType(Limit limit, String request) throws NothingInsideException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        Optional<Limit> getLimit = limitRepository.findByCategory_Name(limit.getCategory().getName().toLowerCase());

        if (getLimit.isPresent()) {
            Limit limitToCheck = limitRepository.findByCategoryIdAndUser(getLimit.get().getCategory().getId(), user);
            LimitDTO limitDTO = LimitDTO
                    .builder()
                    .category(getLimit.get().getCategory().getName())
                    .expenseLimit(limitToCheck.getExpenseLimit())
                    .start(limitToCheck.getStart())
                    .end(limitToCheck.getEnd())
                    .build();
            return limitHandler.getLimitResponseDTO(limitDTO, user);
        } else {
            throw new NothingInsideException("Limit not found for category: " + limit.getCategory().getName());
        }
    }

    @Override
    public MessageDTO deleteLimit(LimitDTO limitDTO, String request) {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        Optional<Limit> limitToDelete = limitHandler.getLimitByCategoryName(limitDTO, user);
        if (limitToDelete.isPresent()) {
            limitRepository.delete(limitToDelete.get());
            return MessageDTO.builder()
                    .message("Your '" + limitDTO.getCategory().toLowerCase() + "' category limit was deleted!").build();
        } else {
            return MessageDTO.builder()
                    .message("You don't have a '" + limitDTO.getCategory().toLowerCase() + "' category limit!").build();
        }
    }

}