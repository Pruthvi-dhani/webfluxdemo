package com.webflux.demo.sec02.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Table
public class Customer {
    @Id
    private Integer id;

    private String name;

    private String email;
}
