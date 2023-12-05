package com.wizer.booklibrary.service.impl;

import com.wizer.booklibrary.dto.BaseResponse;
import com.wizer.booklibrary.dto.BookRequest;
import com.wizer.booklibrary.dto.BookResponse;
import com.wizer.booklibrary.entity.Book;
import com.wizer.booklibrary.entity.Category;
import com.wizer.booklibrary.exception.BookNotFoundException;
import com.wizer.booklibrary.exception.CategoryNotFoundException;
import com.wizer.booklibrary.repository.BookRepository;
import com.wizer.booklibrary.repository.CategoryRepository;
import com.wizer.booklibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BaseResponse saveBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .rating(bookRequest.getRating())
                .build();

        if (bookRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(bookRequest.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " +
                            bookRequest.getCategoryId(), HttpStatus.NOT_FOUND));

            book.setCategory(category);
        }
        Book savedBook = bookRepository.save(book);
        BookResponse bookResponse = modelMapper.map(savedBook, BookResponse.class);

        return BaseResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Book Saved Successfully")
                .result(bookResponse)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse editBook(Long bookId, BookRequest bookRequest) {
        Optional<Book> existingBook = bookRepository.findById(bookId);
        if (existingBook.isPresent()) {
            Book bookExist = existingBook.get();
            bookExist.setTitle(bookRequest.getTitle());
            bookExist.setRating(bookRequest.getRating());
            bookExist.setAuthor(bookRequest.getAuthor());

            Book updatedBook = bookRepository.save(bookExist);
            BookResponse responseDto = modelMapper.map(updatedBook, BookResponse.class);
            return BaseResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Book Updated Successfully")
                    .result(responseDto)
                    .build();
        } else {
            throw new BookNotFoundException("Book not found with id: " + bookId, HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public List<BookResponse> bookList() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookResponse.class)).toList();
    }

    @Override
    public List<BookResponse> listFavoriteBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(favoriteBooks -> favoriteBooks.getRating() != null && favoriteBooks.getRating() > 3)
                .map(favorites -> modelMapper.map(favorites, BookResponse.class))
                .toList();
    }

    public BookResponse convertBookToBaseResponse(Book book) {
        return modelMapper.map(book, BookResponse.class);
    }

    @Override
    public BaseResponse addBookToCategory(Long bookId, Long categoryId) {
        //get bookId,
        Book book=bookRepository.findById(bookId)
                .orElseThrow(()->new BookNotFoundException("Book Not Foud Found with id "+bookId,HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found with id " + categoryId, HttpStatus.NOT_FOUND));
        book.setCategory(category);
          BookRequest bookRequest= modelMapper.map(book,BookRequest.class);

        return saveBook(bookRequest);

    }

    @Override
    @Transactional
    public BaseResponse deleteBook(Long bookId) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId,HttpStatus.NOT_FOUND));
        bookRepository.delete(existingBook);
        return BaseResponse.builder()
                .status(HttpStatus.OK)
                .message("Book deleted successfully")
                .build();
    }
    }



