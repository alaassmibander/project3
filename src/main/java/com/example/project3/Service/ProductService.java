package com.example.project3.Service;

import com.example.project3.Moudle.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ResponseEntity updateProduct(Product product, String id) {
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getId().equals(id)) {
                products.set(i, product);
                return ResponseEntity.status(200).body("product with id: " + id + " was updated.");
            }
        return ResponseEntity.status(404).body("product with id: " + id + " not found.");
    }

    public ResponseEntity deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return ResponseEntity.status(200).body("product with id: " + id + " was deleted.");
            }
        return ResponseEntity.status(400).body("product with id: " + id + " not found.");
    }
}
