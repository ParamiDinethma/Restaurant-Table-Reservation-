package com.restaurant.reservationsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Lombok annotation

@Entity // Marks this class as a JPA entity (maps to a database table)
@Data   // Lombok: Generates getters, setters, toString, equals, and hashCode
public class Table {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Table 1", "Window Seat 5"
    private int capacity; // Maximum number of people

    // Default constructor is required by JPA
    public Table() {}
}
