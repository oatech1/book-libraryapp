package com.wizer.booklibrary.service;

import com.wizer.booklibrary.dto.BaseResponse;
import com.wizer.booklibrary.dto.BookRequest;
import com.wizer.booklibrary.dto.BookResponse;
import com.wizer.booklibrary.entity.Book;

import java.util.List;

public interface BookService {
    BaseResponse saveBook(BookRequest bookRequest);
    BaseResponse editBook(Long bookId, BookRequest bookRequest);
    List<BookResponse> bookList();
    BookResponse convertBookToBaseResponse(Book book);
    BaseResponse addBookToCategory(Long bookId, Long categoryId);
    BaseResponse deleteBook(Long bookId);

    List<BookResponse> listFavoriteBooks();
}
