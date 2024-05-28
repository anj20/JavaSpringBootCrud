package com.example.feedbackmanagement.model;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;
}
