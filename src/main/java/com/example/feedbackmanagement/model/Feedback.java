package com.example.feedbackmanagement.model;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String message;
    private Integer rating;
    private Timestamp submittedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
