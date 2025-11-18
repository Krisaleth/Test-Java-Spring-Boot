package com.k23cnt2.kntlab08.repository;

import com.k23cnt2.kntlab08.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
