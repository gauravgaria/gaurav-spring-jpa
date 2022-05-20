package com.springdatajpa.springboot.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity   // specifies class as JPA entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( name = "products",
        schema = "course_jpa",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "sku_unique",            // unique for key
                    columnNames = "stock_keeping_unit"             // variable name or column name
            )
        })
public class Product {

    @Id         // needed to create PK to map with hibernate table in DB
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long Id;
    @Column(name = "stock_keeping_unit", nullable = false)  // defines column name
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private String  imageUrl;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

}
