package com.restaurant.reservationsystem.repository;

import com.restaurant.reservationsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Standard methods like save(), findById(), and findAll() are inherited.
    // We can add custom finder methods here later, e.g.,
    // Customer findByEmail(String email);
}