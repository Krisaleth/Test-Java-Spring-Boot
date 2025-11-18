package com.k23cnt2.kntlab08.repository;

import com.k23cnt2.kntlab08.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
