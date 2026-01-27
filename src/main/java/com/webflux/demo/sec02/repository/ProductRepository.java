package com.webflux.demo.sec02.repository;

import com.webflux.demo.sec01.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
    Flux<Product> findByPriceBetween(int lowerRange, int upperRange);
}
