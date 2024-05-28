package com.example.feedbackmanagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import com.example.feedbackmanagement.repository.FeedbackRepository;
import com.example.feedbackmanagement.repository.UserRepository;
import com.example.feedbackmanagement.repository.ProductRepository;
import com.example.feedbackmanagement.model.Feedback;
import com.example.feedbackmanagement.model.User;
import com.example.feedbackmanagement.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FeedbackRepositoryTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private Feedback feedback;
    private User user;
    private Product product;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setEmail("john.doe@example.com");
        user.setCreatedAt(Timestamp.from(Instant.now()));
        userRepository.save(user);

        product = new Product();
        product.setName("Product 1");
        product.setDescription("Description for product 1");
        product.setPrice(29.99);
        product.setCreatedAt(Timestamp.from(Instant.now()));
        productRepository.save(product);

        feedback = new Feedback();
        feedback.setCustomerName("John Doe");
        feedback.setEmail("john.doe@example.com");
        feedback.setMessage("Great product!");
        feedback.setRating(5);
        feedback.setSubmittedAt(Timestamp.from(Instant.now()));
        feedback.setProduct(product);
        feedback.setUser(user);
        feedbackRepository.save(feedback);
    }

    @Test
    public void testCreateFeedback() {
        Feedback savedFeedback = feedbackRepository.save(feedback);
        assertThat(savedFeedback).isNotNull();
        assertThat(savedFeedback.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAllFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        assertThat(feedbacks).hasSize(1);
    }

    @Test
    public void testFindFeedbackById() {
        Optional<Feedback> foundFeedback = feedbackRepository.findById(feedback.getId());
        assertThat(foundFeedback).isPresent();
        assertThat(foundFeedback.get().getCustomerName()).isEqualTo(feedback.getCustomerName());
    }

    @Test
    public void testUpdateFeedback() {
        feedback.setMessage("Updated feedback message");
        Feedback updatedFeedback = feedbackRepository.save(feedback);
        assertThat(updatedFeedback.getMessage()).isEqualTo("Updated feedback message");
    }

    @Test
    public void testDeleteFeedback() {
        feedbackRepository.delete(feedback);
        Optional<Feedback> deletedFeedback = feedbackRepository.findById(feedback.getId());
        assertThat(deletedFeedback).isNotPresent();
    }
}
