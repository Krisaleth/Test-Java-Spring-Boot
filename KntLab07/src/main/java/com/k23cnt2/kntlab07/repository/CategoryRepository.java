package com.k23cnt2.kntlab07.repository;

import com.k23cnt2.kntlab07.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
