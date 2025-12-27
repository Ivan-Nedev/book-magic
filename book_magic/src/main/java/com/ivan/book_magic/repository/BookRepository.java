package com.ivan.book_magic.repository;

import com.ivan.book_magic.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<BookEntity,Long> {
    boolean existsByProductUrl(String name);
    List<BookEntity> findAllByAuthor(String name);
}
