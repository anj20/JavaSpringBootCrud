package com.example.feedbackmanagement.controller;

import com.example.feedbackmanagement.model.Feedback;
import com.example.feedbackmanagement.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback existingFeedback = feedbackRepository.findById(id).orElse(null);
        if (existingFeedback != null) {
            existingFeedback.setCustomerName(feedback.getCustomerName());
            existingFeedback.setEmail(feedback.getEmail());
            existingFeedback.setMessage(feedback.getMessage());
            existingFeedback.setRating(feedback.getRating());
            existingFeedback.setSubmittedAt(feedback.getSubmittedAt());
            existingFeedback.setProduct(feedback.getProduct());
            existingFeedback.setUser(feedback.getUser());
            return feedbackRepository.save(existingFeedback);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackRepository.deleteById(id);
    }
}
