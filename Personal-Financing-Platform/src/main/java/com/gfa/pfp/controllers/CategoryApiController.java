package com.gfa.pfp.controllers;

import com.gfa.pfp.exception.UnauthorizedException;
import com.gfa.pfp.exception.financeexceptions.FinanceErrorException;
import com.gfa.pfp.exception.financeexceptions.FinanceNotFoundException;
import com.gfa.pfp.models.dto.finance.CategoryDTO;
import com.gfa.pfp.models.dto.finance.GetAllCategoriesDTO;
import com.gfa.pfp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    ResponseEntity<CategoryDTO> createNewCategory(@RequestBody CategoryDTO categoryDto,
                                                  @RequestHeader String authorization)
            throws FinanceErrorException {
        return ResponseEntity.ok().body(categoryService.createNewCategory(categoryDto, authorization));
    }

    @GetMapping("/getAll")
    ResponseEntity<GetAllCategoriesDTO> findAllCategories(@RequestHeader String authorization)
            throws FinanceNotFoundException {
        return ResponseEntity.ok().body(categoryService.readAllCategories(authorization));
    }

    @PatchMapping("/update/{id}")
    ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDto,
                                               @RequestHeader String authorization,
                                               @PathVariable Long id)
            throws FinanceNotFoundException, UnauthorizedException {
        return ResponseEntity.ok().body(categoryService.updateCategory(id, categoryDto, authorization));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteCategory(@RequestHeader String authorization, @PathVariable Long id)
            throws FinanceNotFoundException, UnauthorizedException {
        categoryService.deleteCategory(id, authorization);
        return ResponseEntity.ok().build();

    }

}