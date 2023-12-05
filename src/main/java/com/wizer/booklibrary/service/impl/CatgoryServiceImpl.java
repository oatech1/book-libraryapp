package com.wizer.booklibrary.service.impl;

import com.wizer.booklibrary.dto.*;
import com.wizer.booklibrary.entity.Book;
import com.wizer.booklibrary.entity.Category;
import com.wizer.booklibrary.exception.BookNotFoundException;
import com.wizer.booklibrary.exception.CategoryNotFoundException;
import com.wizer.booklibrary.repository.CategoryRepository;
import com.wizer.booklibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatgoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BaseResponse saveCategory(CategoryRequest categoryRequest) {
         Category category =  modelMapper.map(categoryRequest, Category.class);
           Category savedCategory=categoryRepository.save(category);
       CategoryResponse categoryResponse=modelMapper.map(savedCategory, CategoryResponse.class);
        return BaseResponse.builder()
                .status(HttpStatus.CREATED)
                .message("")
                .result(categoryResponse)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse editCategory(Long categoryId, CategoryRequest categoryRequest) {
       Optional<Category> optionalCategory=  categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Category categoryExist=optionalCategory.get();
            categoryExist.setName(categoryRequest.getName());


            Category updatedCategory = categoryRepository.save(categoryExist);
            CategoryResponse responseDto = modelMapper.map(updatedCategory, CategoryResponse.class);
            return BaseResponse.builder()
                    .status(HttpStatus.OK)
                    .message("")
                    .result(responseDto)
                    .build();
        }else {
          throw new CategoryNotFoundException("Category not found with id: "+categoryId, HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public List<CategoryResponse> categoryList() {
         return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class)).toList();

    }
    @Override
    public CategoryResponse convertCategoryToBaseResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    @Transactional
    public BaseResponse deleteCategory(Long categoryId) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + categoryId,HttpStatus.NOT_FOUND));
       categoryRepository.delete(existingCategory);
        return BaseResponse.builder()
                .status(HttpStatus.OK)
                .message("Category deleted successfully")
                .build();
    }
}
