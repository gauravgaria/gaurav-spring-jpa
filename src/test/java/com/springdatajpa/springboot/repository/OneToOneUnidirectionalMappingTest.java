package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    void saveOrderMethod(){

        Order order = new Order();
        order.setOrderTrackingNumber("1000ABS");
        order.setStatus("In progress");
        order.setTotalQuantity(5);
        order.setTotalPrice(new BigDecimal(1000));

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
    void saveAddressMethod(){

        Order order = new Order();
        order.setOrderTrackingNumber("1000ABS");
        order.setStatus("In progress");
        order.setTotalQuantity(5);
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Gurgaon");
        address.setCountry("India");
        address.setPincode("122001");
        address.setStreet("Sector 9A");
        address.setState("Haryana");

        // set order billing address
        order.setBillingAddress(address);
        address.setOrder(order);

        addressRepository.save(address);
    }

    @Test
    void updateAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        address.setPincode("122011");

        // updating order values using address object
        address.getOrder().setStatus("Delivered");

        addressRepository.save(address);
    }

    @Test
    void fetchAddressMethod(){
        Address address = addressRepository.findById(2L).get();
    }

    @Test
    void deleteAddressMethod(){
        addressRepository.deleteById(1L);
    }
}
