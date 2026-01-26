package com.webflux.demo.sec02;

import com.webflux.demo.sec02.entity.Customer;
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

    @Test
    public void insertAndDeleteTest() {
        var customer = new Customer();
        customer.setName("Johnny");
        customer.setEmail("jons.erdos@gmail.com");
        customerRepository.save(customer)
                .doOnNext(item -> log.info("Received: {}", item))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();

        // test count
        customerRepository.count()
                .as(StepVerifier::create)
                .assertNext(i -> Assertions.assertEquals(11, i))
                .expectComplete()
                .verify();

        // delete the created object
        customerRepository.deleteById(11)
                .then(customerRepository.count())
                .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();
    }
}
