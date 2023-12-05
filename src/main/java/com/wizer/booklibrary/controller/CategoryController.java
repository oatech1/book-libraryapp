package com.wizer.booklibrary.controller;

import com.wizer.booklibrary.dto.*;
import com.wizer.booklibrary.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Add Category")
    public Mono<BaseResponse> addCategory(@RequestBody CategoryRequest request) {
        return Mono.just(categoryService.saveCategory(request));
    }

    @PutMapping("/category/{categoryId}")
    @Operation(summary = "Edit Category")
    public Mono<BaseResponse> editCategory( @PathVariable Long categoryId, @RequestBody CategoryRequest request) {
        return Mono.just(categoryService.editCategory(categoryId,request));
    }
    @GetMapping
    @Operation(summary = "List Categories")
    public Flux<CategoryResponse> getAllCategories() {
      return   Flux.fromIterable(categoryService.categoryList());

    }
    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Delete  Category")
    public Mono<BaseResponse> deleteBook(@PathVariable Long categoryId) {
        return Mono.just(categoryService.deleteCategory(categoryId));
    }


}
