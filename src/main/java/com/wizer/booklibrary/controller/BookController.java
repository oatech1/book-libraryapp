package com.wizer.booklibrary.controller;

import com.wizer.booklibrary.dto.BaseResponse;
import com.wizer.booklibrary.dto.BookRequest;
import com.wizer.booklibrary.dto.BookResponse;
import com.wizer.booklibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @PostMapping
    @Operation(summary = "Add Book")
    public Mono<BaseResponse> addBook(@RequestBody BookRequest request) {
        return Mono.just(bookService.saveBook(request));
    }

    @PutMapping("/{bookId}")
    @Operation(summary = "Edit Book")
    public Mono<BaseResponse> editBook( @PathVariable Long bookId, @RequestBody BookRequest request) {
        return Mono.just(bookService.editBook(bookId,request));
    }
    @GetMapping
    @Operation(summary = "List Books")
    public Flux<BookResponse> getAllBooks() {
      return   Flux.fromIterable(bookService.bookList());

    }
    @PostMapping("/addbooktocat/{bookId}/{categoryId}")
    @Operation(summary = "Add Books to Category")
    public Mono<BaseResponse> addBookToCategory(@PathVariable Long bookId, @PathVariable Long categoryId) {
        return Mono.just(bookService.addBookToCategory(bookId,categoryId));
    }

    @GetMapping("/listoffavoritebook")
    @Operation(summary = "List of Favorite books")
    public Flux<BookResponse> listFavoriteBooks() {
        return Flux.fromIterable(bookService.listFavoriteBooks());

    }
    @DeleteMapping("/{bookId}")
    @Operation(summary = "Delete  Book")
    public Mono<BaseResponse> deleteBook(@PathVariable Long bookId) {
        return Mono.just(bookService.deleteBook(bookId));
    }

}
