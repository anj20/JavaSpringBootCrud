package com.example.feedbackmanagement.model;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Timestamp createdAt;

    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks;
}
