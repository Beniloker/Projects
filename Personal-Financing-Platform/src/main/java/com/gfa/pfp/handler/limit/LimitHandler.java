package com.gfa.pfp.handler.limit;

import com.gfa.pfp.models.dto.limit.LimitDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseDTO;
import com.gfa.pfp.models.entities.finance.Category;
import com.gfa.pfp.models.entities.finance.Finance;
import com.gfa.pfp.models.entities.finance.Limit;
import com.gfa.pfp.models.entities.user.User;
import com.gfa.pfp.repositories.CategoryRepository;
import com.gfa.pfp.repositories.FinanceRepository;
import com.gfa.pfp.repositories.LimitRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LimitHandler {

    private final LimitRepository limitRepository;
    private final FinanceRepository financeRepository;
    private final CategoryRepository categoryRepository;

    public Optional<Limit> getLimitByCategoryName(LimitDTO limitDTO, User user) {
        Optional<Category> category = categoryRepository.findByUserIdAndName(user.getId(), limitDTO.getCategory().toLowerCase());
        Optional<Category> defaultCategory = categoryRepository.findByUserIdAndName(-1L, limitDTO.getCategory().toLowerCase());
        if (category.isPresent()) {
            return limitRepository.findByCategoryAndUser(category.get(), user);
        } else
            return defaultCategory.map(value -> limitRepository.findByCategoryAndUser(value, user)).orElse(null);
    }

    public Optional<Category> getCategoryByName(LimitDTO limitDTO, Long userId) {
        Optional<Category> category = categoryRepository.findByUserIdAndName(userId, limitDTO.getCategory().toLowerCase());
        Optional<Category> defaultCategory = categoryRepository.findByUserIdAndName(-1L, limitDTO.getCategory().toLowerCase());
        if (category.isPresent()) {
            return category;
        } else
            return defaultCategory;
    }

    public LimitResponseDTO getLimitResponseDTO(LimitDTO limit, User user) {
        List<Finance> expList = financeRepository
                .findByUserIdAndCategoryIdAndTransactionDateBetween(
                        user.getId(),
                        getCategoryByName(limit, user.getId()).get().getId(),
                        limit.getStart(),
                        limit.getEnd());
        double currentSum = expList
                .stream()
                .mapToDouble(Finance::getExpense)
                .reduce(0.0, Double::sum);
        double percentageD = BigDecimal
                .valueOf(currentSum)
                .divide(BigDecimal.valueOf(limit
                        .getExpenseLimit()), 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100)).doubleValue();
        double percentageRemainingD = BigDecimal
                .valueOf(100).subtract(BigDecimal.valueOf(percentageD)).doubleValue();
        double remainingD = BigDecimal
                .valueOf(limit.getExpenseLimit())
                .subtract(BigDecimal.valueOf(currentSum)).doubleValue();
        String remainingDS = Double.toString(remainingD);
        String percentageRemDS = percentageRemainingD + " %";

        if (remainingD < 0 && percentageRemainingD < 0) {
            remainingD = remainingD * -1;
            remainingDS = "Your limit is exceeded by " + remainingD;
            percentageRemainingD = percentageRemainingD * -1;
            percentageRemDS = "Your limit is exceeded by " + percentageRemainingD + " %";
        }
        return LimitResponseDTO.builder()
                .category(getCategoryByName(limit, user.getId()).get().getName())
                .limit(limit.getExpenseLimit())
                .current(currentSum)
                .remaining(remainingDS)
                .percentage(percentageD + " %")
                .percentageRemaining(percentageRemDS)
                .start(limit.getStart())
                .end(limit.getEnd())
                .build();
    }

}