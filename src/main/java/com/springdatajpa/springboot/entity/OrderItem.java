package com.springdatajpa.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //bidirectional one-many/many-one mapping

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item", referencedColumnName = "id")
    private Order order;


}
