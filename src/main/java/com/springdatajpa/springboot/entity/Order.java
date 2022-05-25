package com.springdatajpa.springboot.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/** @Data annotation means these all-> @Getter
                              @Setter
                              @NoArgsConstructor
                              @AllArgsConstructor
                              @ToString
 * */

@Entity   // specifies class as JPA entity
@Data
@Table( name = "orders")
public class Order {
    @Id         // needed to create PK to map with hibernate table in DB
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    /** Cascading in JPA -> updates the row in both parent and child table
     *  delete order then address also gets delete corresponding to that foreign key.
     * */
   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="billing_address_id", referencedColumnName = "id")
    private Address billingAddress;*/

    /** One to One Bi-directional mapping
     *  Join column is in child class
     * */

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;


    /** Difference between EAGER and LAZY
     * Eager -> loads all dependent entities | performance issues : will load all child data also | use : load parent with child
     * Lazy -> laods main entity not dependents | on demand |
     * */

    // Set of order items
    // by default fetch type for one to many is LAZY

    /** one to many bidirectional example
     * */
   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_item", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();*/

    /** one to many bidirectional example
     * */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();


    public BigDecimal getTotalAmount(){
        BigDecimal amount = new BigDecimal(0.0);
        for(OrderItem orderItem : this.orderItems)
            amount = amount.add(orderItem.getPrice().multiply(new BigDecimal(totalQuantity)));

        return amount;
    }
}
