package com.example.project3.Service;

import com.example.project3.Moudle.Product;
import com.example.project3.Moudle.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    MerchantStockService merchantStockService;
    ProductService productService;

    public UserService(MerchantStockService merchantStockService, ProductService productService) {
        this.merchantStockService = merchantStockService;
        this.productService = productService;
    }


    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ResponseEntity updateUser(User user, String id) {
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return ResponseEntity.status(200).body("user with id: " + id + " was updated.");
            }
        return ResponseEntity.status(404).body("user with id: " + id + " not found.");
    }

    public ResponseEntity deleteUser(String id) {
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return ResponseEntity.status(200).body("user with id: " + id + " was deleted.");
            }
        return ResponseEntity.status(400).body("user with id: " + id + " not found.");
    }

    public ResponseEntity addStock(String productId, String merchantId, int stock) {

        if (stock < 1) {
            return ResponseEntity.status(400).body("Stock must be positive");
        }

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId)
                    && merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)) {

                int currentStock = merchantStockService.merchantStocks.get(i).getStock();
                merchantStockService.merchantStocks.get(i).setStock(currentStock + stock);

                return ResponseEntity.status(200).body("Stock was added.");
            }
        }
        return ResponseEntity.status(400).body("product from merchant was not found");
    }


    public ResponseEntity buy(String userid, String productId, String merchantId, int quantity) {

        User buyer = null;
        boolean productExists = false;
        boolean hasBalance = false;
        double totalCost = 0;


        if (quantity < 1) {
            return ResponseEntity.status(400).body("Stock to buy must be positive");
        }


        for (Product product : productService.products) {
            if (product.getId().equals(productId)) {
                totalCost = product.getPrice() * quantity;
                productExists = true;
            }
        }

        if (!productExists)
            return ResponseEntity.status(400).body("product with id: " + productId + " from merchant with id: " + merchantId + " was not found");


        for (User user : users) {
            if (user.getId().equals(userid)) {
                buyer = user;
                if (user.getBalance() >= totalCost)
                    hasBalance = true;
            }
        }
        if (!hasBalance)
            return ResponseEntity.status(400).body("Insufficient balance");
        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId)
                    && merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)) {

                int currentStock = merchantStockService.merchantStocks.get(i).getStock();

                if (currentStock >= quantity) {
                    merchantStockService.merchantStocks.get(i).setStock(currentStock - quantity);
                    buyer.setBalance(buyer.getBalance() - totalCost);
                    return ResponseEntity.status(200).body("Order was fulfilled.");
                } else {
                    return ResponseEntity.status(400).body("Not enough stock for the order. Current stock: " + currentStock + ". Order quantity: " + quantity + ".");
                }
            }
        }
        return ResponseEntity.status(400).body("product with id: " + productId + " from merchant with id: " + merchantId + " was not found");


    }


}
