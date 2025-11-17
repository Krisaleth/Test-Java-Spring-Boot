package com.k23cnt2.kntlab07.repository;

import com.k23cnt2.kntlab07.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
