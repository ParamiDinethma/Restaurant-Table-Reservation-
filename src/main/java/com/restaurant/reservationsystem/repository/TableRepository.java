package com.restaurant.reservationsystem.repository;

import com.restaurant.reservationsystem.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    // Spring Data JPA automatically generates the query for this based on the method name.
    // It translates to: SELECT * FROM Table WHERE capacity >= ?
    List<Table> findByCapacityGreaterThanEqual(int capacity);
}
