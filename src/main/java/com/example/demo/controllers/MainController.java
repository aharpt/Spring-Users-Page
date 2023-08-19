package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ProductRepository productRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path="/products")
  public @ResponseBody Iterable<Object> getProductsByUser() {
    return productRepository.getProductsByUser();
    // return productRepository.findAll();
  }

  @DeleteMapping(path="/delete")
  public @ResponseBody String deleteUser(@RequestParam int id) {
    Optional<User> foundUser = userRepository.findById(id);

    try {
      userRepository.deleteById(id);
      return "User with ID: " + id + " was successfully deleted";
    } catch (Error error) {
      return "User with ID: " + id + " could not be deleted";
    }
  }

  @PutMapping("/update")
  public @ResponseBody String updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String email) {
    Optional<User> foundUser = userRepository.findById(id);
    try {
      User user = foundUser.get();
      user.setName(name);
      user.setEmail(email);
      userRepository.save(user);
      return "User " + user.getName() + " Updated";
    } catch (Error error) {
      return "User with ID " + id + " not found";
    }
  }
}
