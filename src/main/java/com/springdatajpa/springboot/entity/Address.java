package com.springdatajpa.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity   // specifies class as JPA entity
@Data
@Table( name = "addresses")
public class Address {
        @Id         // needed to create PK to map with hibernate table in DB
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long Id;
        private String street;
        private String city;
        private String state;
        private String country;
        private String pincode;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="order_id", referencedColumnName = "id")
        private Order order;
}
