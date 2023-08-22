package com.gfa.pfp.services;

import com.gfa.pfp.exception.UnauthorizedException;
import com.gfa.pfp.exception.financeexceptions.FinanceErrorException;
import com.gfa.pfp.exception.financeexceptions.FinanceNotFoundException;
import com.gfa.pfp.models.dto.finance.CategoryDTO;
import com.gfa.pfp.models.dto.finance.GetAllCategoriesDTO;
import com.gfa.pfp.models.entities.finance.Category;
import com.gfa.pfp.models.entities.user.User;
import com.gfa.pfp.repositories.CategoryRepository;
import com.gfa.pfp.repositories.UserRepository;
import com.gfa.pfp.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final JwtService jwtService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDto, String request) throws FinanceErrorException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        if ((Objects.isNull(categoryDto.getCategoryName()))
                || categoryDto.getCategoryName().trim().equals("")) {
            throw new FinanceErrorException("A category must be given");
        }
        if (categoryRepository.findByUserIdAndName(user.getId(), categoryDto.getCategoryName().toLowerCase()).isPresent()) {
            throw new FinanceErrorException("Category '" + categoryDto.getCategoryName().toLowerCase() + "' already exists");
        }
        Category newCategory = new Category(user.getId(), categoryDto.getCategoryName().toLowerCase());
        categoryRepository.save(newCategory);
        categoryDto.setId((long) categoryRepository.findAll().toArray().length);
        return categoryDto;
    }

    @Override
    public GetAllCategoriesDTO readAllCategories(String request) throws FinanceNotFoundException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        List<Category> categoryList = categoryRepository.findAllByUserId(user.getId());
        categoryList.addAll(categoryRepository.findAllByUserId(-1L));
        if (categoryList.isEmpty()) {
            throw new FinanceNotFoundException("No category has been found for user with id: " + user.getId());
        }
        HashMap<Long, CategoryDTO> categoryDtoHashMap = new HashMap<>();
        for (Category category : categoryList) {
            if (Objects.isNull(category.getName())) {
                continue;
            }
            categoryDtoHashMap.put(category.getId(),
                    CategoryDTO.builder()
                            .id(category.getId())
                            .categoryName(category.getName())
                            .build());
        }
        return categoryDtoHashMap.isEmpty() ? null : GetAllCategoriesDTO.builder()
                .categories(categoryDtoHashMap)
                .build();
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDto, String request) throws FinanceNotFoundException, UnauthorizedException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        Category category = checkAndFindCategory(id, user);
        category.setName(categoryDto.getCategoryName());
        categoryRepository.save(category);
        return CategoryDTO.builder()
                .id(category.getId())
                .categoryName(category.getName())
                .build();
    }

    @Override
    public void deleteCategory(Long id, String request) throws FinanceNotFoundException, UnauthorizedException {
        User user = userRepository.findByEmailOrderById(jwtService.extractUserNameFromHeader(request));
        Category category = checkAndFindCategory(id, user);
        categoryRepository.delete(category);

    }

    private Category checkAndFindCategory(Long id, User user) throws FinanceNotFoundException, UnauthorizedException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new FinanceNotFoundException("Category with id '" + id + "' not found.");
        }
        categoryOptional = categoryRepository.findByIdAndUserId(id, user.getId());
        if (categoryOptional.isEmpty()) {
            throw new UnauthorizedException("You are not authorized to category Nr. '" + id + "'");
        }
        return categoryOptional.get();
    }
}
