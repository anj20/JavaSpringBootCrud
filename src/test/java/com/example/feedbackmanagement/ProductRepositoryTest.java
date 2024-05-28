package com.example.feedbackmanagement;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.example.feedbackmanagement.repository.ProductRepository;
import com.example.feedbackmanagement.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setName("Product 1");
        product.setDescription("Description for product 1");
        product.setPrice(29.99);
        product.setCreatedAt(Timestamp.from(Instant.now()));
        productRepository.save(product);
    }

    @Test
    public void testCreateProduct() {
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAllProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
    }

    @Test
    public void testFindProductById() {
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo(product.getName());
    }

    @Test
    public void testUpdateProduct() {
        product.setName("Updated Product");
        Product updatedProduct = productRepository.save(product);
        assertThat(updatedProduct.getName()).isEqualTo("Updated Product");
    }

    @Test
    public void testDeleteProduct() {
        productRepository.delete(product);
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertThat(deletedProduct).isNotPresent();
    }
}
