package com.wizer.booklibrary.service;

import com.wizer.booklibrary.dto.*;
import com.wizer.booklibrary.entity.Category;

import java.util.List;

public interface CategoryService {
    BaseResponse saveCategory(CategoryRequest categoryRequest);
    BaseResponse editCategory(Long categoryId, CategoryRequest categoryRequest);
    public List<CategoryResponse> categoryList();
    CategoryResponse convertCategoryToBaseResponse(Category category);
    BaseResponse deleteCategory(Long categoryId);
}
