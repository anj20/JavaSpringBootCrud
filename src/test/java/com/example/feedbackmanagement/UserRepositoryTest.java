package com.example.feedbackmanagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import com.example.feedbackmanagement.repository.UserRepository;
import com.example.feedbackmanagement.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setEmail("john.doe@example.com");
        user.setCreatedAt(Timestamp.from(Instant.now()));
        userRepository.save(user);
    }

    @Test
    public void testCreateUser() {
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    public void testFindUserById() {
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testUpdateUser() {
        user.setUsername("john_updated");
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser.getUsername()).isEqualTo("john_updated");
    }

    @Test
    public void testDeleteUser() {
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertThat(deletedUser).isNotPresent();
    }
}
