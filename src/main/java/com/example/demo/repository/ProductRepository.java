package com.example.demo.repository;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

  @Query(value = "SELECT u.name, p.name, p.cost\n" +
    "FROM products p\n" +
    "JOIN `user` u \n" +
    "ON p.userID = u.id;", nativeQuery = true)
  Iterable<Object> getProductsByUser();
}
