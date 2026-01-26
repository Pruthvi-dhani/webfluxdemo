package com.webflux.demo.sec02;

import com.webflux.demo.sec02.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@Slf4j
public class CustomerRepositoryTest extends AbstractTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAllTest() {
        customerRepository.findAll().doOnNext(item -> log.info("Received: {}", item))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }

    @Test
    public void findByIdTest() {
        customerRepository.findById(2)
                .doOnNext(item -> log.info("Received: {}", item))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByNameTest() {
        customerRepository.findByName("jake")
                .doOnNext(item -> log.info("Received: {}", item))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    public void findByEmailSuffixTest() {
        customerRepository.findByEmailEndingWith("ke@gmail.com")
                .doOnNext(item -> log.info("Received: {}", item))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }



}
