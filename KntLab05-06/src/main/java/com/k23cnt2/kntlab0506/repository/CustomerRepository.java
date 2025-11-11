package com.k23cnt2.kntlab0506.repository;

import com.k23cnt2.kntlab0506.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
