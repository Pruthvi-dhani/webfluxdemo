package com.webflux.demo.sec02.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
@ToString
public class Product {
    @Id
    private Integer id;

    private String description;

    private Integer price;
}
