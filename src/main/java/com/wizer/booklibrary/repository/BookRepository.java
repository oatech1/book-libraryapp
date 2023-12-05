package com.wizer.booklibrary.repository;

import com.wizer.booklibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
