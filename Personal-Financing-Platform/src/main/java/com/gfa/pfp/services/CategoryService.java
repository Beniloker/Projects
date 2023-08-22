package com.gfa.pfp.services;

import com.gfa.pfp.exception.UnauthorizedException;
import com.gfa.pfp.exception.financeexceptions.FinanceErrorException;
import com.gfa.pfp.exception.financeexceptions.FinanceNotFoundException;
import com.gfa.pfp.models.dto.finance.CategoryDTO;
import com.gfa.pfp.models.dto.finance.GetAllCategoriesDTO;

public interface CategoryService {

    CategoryDTO createNewCategory(CategoryDTO categoryDto, String request) throws FinanceErrorException;

    GetAllCategoriesDTO readAllCategories(String authorization) throws FinanceNotFoundException;

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDto, String request) throws FinanceNotFoundException, UnauthorizedException;

    void deleteCategory(Long id, String request) throws FinanceNotFoundException, UnauthorizedException;

}