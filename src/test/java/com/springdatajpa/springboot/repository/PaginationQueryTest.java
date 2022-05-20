package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationQueryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void pagination(){

        // declare inputs
        int pageNo = 0;
        int pageSize = 5;

        // create pageable object
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        // find all method
        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(p-> System.out.println(p.getName()));


        /** Pagination other information
         * */

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long TotalElements = page.getTotalElements();
        //number of elements
        int numberOfElements = page.getNumberOfElements();
        //size
        int size = page.getSize();

        // last page
        boolean isLastPage = page.isLast();
        // first page
        boolean isFirst = page.isFirst();

        System.out.println("total page " + totalPage);
        System.out.println("total elements " + totalPage);
        System.out.println("number of elements " + numberOfElements);
        System.out.println("size " + size);
        System.out.println("isLast " + isLastPage);
        System.out.println("isFirst " + isFirst);

    }

    @Test
    void sorting(){
        String sortBy = "price";
        String sortDirection = "asc";       //asc or desc

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Product> products = productRepository.findAll(sort);

        products.forEach(p-> System.out.println(p.getName() + " price is " + p.getPrice()));
    }

    @Test
    void sortingByMultipleFields(){
        String byName = "name";
        String byPrice = "price";
        String sortDir = "asc";

        // sort by name
        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(byName).ascending() : Sort.by(byName).descending();

        // sort by price
        Sort sortByPrice = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(byPrice).ascending() : Sort.by(byPrice).descending();

        // group both together using 'and'
        Sort groupBySort = sortByName.and(sortByPrice);

        List<Product> products = productRepository.findAll(groupBySort);

        products.forEach(p-> System.out.println(p));

    }

    void sortingAndPagination(){
        String sortBy = "price";
        String sortDir = "desc";
        int pageNo = 0;
        int pageSize = 5;

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // pagination
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(p-> System.out.println(p));

    }
}
