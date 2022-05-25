package com.springdatajpa.springboot;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.OrderItem;
import com.springdatajpa.springboot.repository.OrderRepository;
import com.springdatajpa.springboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /** One to many unidirectional mapping hibernate executes update statements also : causing performance issue
     * Hence bi-directional mapping is used
     * */

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("10928WA");
        order.setStatus("In Progress");

        // create order 1
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productRepository.findById(1L).get());
        orderItem.setQuantity(2);
        orderItem.setPrice(orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
        orderItem.setImageUrl("prod_image_1.jpg");
        order.getOrderItems().add(orderItem);

        // create order 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(2L).get());
        orderItem1.setQuantity(3);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(orderItem1.getQuantity())));
        orderItem1.setImageUrl("prod_image_2.jpg");
        order.getOrderItems().add(orderItem1);

        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("Gurgaon");
        address.setCountry("India");
        address.setPincode("122001");
        address.setStreet("Sector 9A");
        address.setState("Haryana");

        // set order billing address
        order.setBillingAddress(address);

        orderRepository.save(order);
    }

    @Test
    void fetchOrderMethod(){
        Order order = orderRepository.findById(2L).get();
        System.out.println(order.getStatus());

        // loading order and its dependency
        System.out.println(order.toString());

        order.getOrderItems().forEach(p-> System.out.println(" product name " + p.getProduct().getName() + " price " + p.getPrice()));
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }
}
