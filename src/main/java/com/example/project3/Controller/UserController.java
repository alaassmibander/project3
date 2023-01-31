package com.example.project3.Controller;

import com.example.project3.Moudle.User;
import com.example.project3.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User: " + user.getUsername() + " was added.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid User user, @PathVariable String id, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ResponseEntity result = userService.updateUser(user, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        ResponseEntity result = userService.deleteUser(id);

        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }

    @PutMapping("/stock/add/{merchantId}/{productId}/{stock}")
    public ResponseEntity addStock(@PathVariable String merchantId, @PathVariable String productId, @PathVariable int stock) {
        ResponseEntity result = userService.addStock(productId, merchantId, stock);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }

    @PutMapping("/buy/{userId}/{merchantId}/{productId}/{quantity}")
    public ResponseEntity buy(@PathVariable String userId, @PathVariable String merchantId, @PathVariable String productId, @PathVariable int quantity) {
        ResponseEntity result = userService.buy(userId, productId, merchantId, quantity);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }
}
