package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveProduct(){
        //create product object
        Product product = new Product();
        product.setName("Redmi 5 Pro");
        product.setDescription("Brand new released redmi phone");
        product.setPrice(new BigDecimal(12999));
        product.setSku("1002A");
        product.setActive(true);
        product.setImageUrl("redmi_5_pro.png");

        //save product object
        Product saveObj = productRepository.save(product);

        //log product info
        System.out.println(saveObj.getId() + "saved in database");

        System.out.println(saveObj.toString());
    }

    @Test
    void saveAllProducts(){

        //create product object
        Product product = new Product();
        product.setName("Redmi 5 Pro");
        product.setDescription("Brand new released redmi phone");
        product.setPrice(new BigDecimal(12999));
        product.setSku("1002A");
        product.setActive(true);
        product.setImageUrl("redmi_5_pro.png");

        //product 2
        Product product1 = new Product();
        product1.setName("Motorola G71");
        product1.setDescription("motorola G71 amazing battery life");
        product1.setPrice(new BigDecimal(22999));
        product1.setSku("1014B");
        product1.setActive(true);
        product1.setImageUrl("motorola_g71_1.png");

        productRepository.saveAll(List.of(product,product1));

    }

    @Test
    void saveMultipleProductsLoop(){
        int n = 0;
        List<Product> productList = new ArrayList<>();
        while (n < 100){
           Product product = saveProducts(n);
           productList.add(product);
           n++;
        }
        productRepository.saveAll(productList);
    }

    Product saveProducts(int n){
        Product product = new Product();
        product.setName("Redmi"+ n + "Pro");
        product.setDescription("Brand new released redmi phone" + n + "version");
        product.setPrice(new BigDecimal(12999 + (n * 50)));
        product.setSku("1002A" + n);
        product.setActive(true);
        product.setImageUrl("redmi_"+ n +"_pro.png");

        return product;
    }

    @Test
    void updateProduct(){
        // find and retrieve an entity by id
        Long id = 1L;
        Product product = productRepository.findById(id).get();

        //update entity info
        product.setDescription("redmi 5 pro + is new release");
        product.setName("Redmi 5 pro +");

        // save updated entity
        productRepository.save(product);
    }

    @Test
    void findProduct(){
        // find by ID
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void findAllProducts(){
        List<Product> product = productRepository.findAll();

        product.forEach(p-> System.out.println(p.getName()));
    }

    @Test
    void deleteById(){
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteByObject(){
        Long id = 1L;
        // find the product by ID
        Product product = productRepository.findById(id).get();

        // delete the object
        productRepository.delete(product);
    }

    @Test
    void deleteAllProducts(){
      // delete all products
        productRepository.deleteAll();

      // delete selected products
      Product product = productRepository.findById(1L).get();
      Product product1 = productRepository.findById(2L).get();

      productRepository.deleteAll(List.of(product,product1));
    }

    @Test
    void getProductCount(){
        System.out.println(productRepository.count());
    }

    @Test
    void productExists(){
        long id = 2L;
        System.out.println(productRepository.existsById(id));
    }

    @Test
    void productByName(){
        Product product = productRepository.findByName("Redmi 5 Pro");
        System.out.printf("Name of the product is %s for price %f",product.getName(),product.getPrice().doubleValue());
    }

    @Test
    void getProductById(){

        //Optional<Product> returns optional and .get() returns entity object

        Product product = productRepository.findById(1L).get();
        System.out.printf("Name of the product is %s for price %f",product.getName(),product.getPrice().doubleValue());
    }

    @Test
    void findByNameOrDescription(){
        List<Product> products = productRepository.findByNameOrDescription("Redmi 5 Pro","Brand new released redmi phone");

        products.forEach(p-> System.out.println(p.getName()));
    }

    @Test
    void findByNameAndDescription(){
        List<Product> products = productRepository.findByNameAndDescription("Redmi 5 Pro","Brand new released redmi phone");

        products.forEach(p-> System.out.println(p.getName()));
    }

    @Test
    void findPriceGreaterThan(){
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));
    }

    @Test
    void findPriceLessThan(){
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(20000));
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));
    }

    // like query -> % Redmi note 5 %
    @Test
    void productContaining(){
        List<Product> products = productRepository.findByNameContaining("redmi 5 pro");
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }

    // like query alternative method -> % Redmi note 5 %
    @Test
    void productWithLike(){
        List<Product> products = productRepository.findByNameLike("redmi 5 pro");
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }

    @Test
    void productPriceBetween(){
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(10000),new BigDecimal(20000));
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }

    //between date range
    @Test
    void productDateBetween(){
        // start date
        LocalDateTime startDate = LocalDateTime.of(2022,05,13,07,52,04);

        // end date
        LocalDateTime endDate = LocalDateTime.of(2022,05,15,07,52,04);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate,endDate);

        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }

    @Test
    void findByProductsInMethod(){
        List<Product> products = productRepository.findByNameIn(List.of("Redmi 5 pro","Redmi 5 pro +"));

        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }

    @Test
    void findByFirstTwoOrderByAsc(){
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();

        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }

    @Test
    void findTopOrderByDesc(){
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();

        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));

    }
}