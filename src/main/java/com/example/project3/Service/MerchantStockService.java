package com.example.project3.Service;

import com.example.project3.Moudle.MerchantStock;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getAllMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public ResponseEntity updateMerchantStock(MerchantStock merchantStock, String id) {
        for (int i = 0; i < merchantStocks.size(); i++)
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.set(i, merchantStock);
                return ResponseEntity.status(200).body("merchantStock with id: " + id + " was updated.");
            }
        return ResponseEntity.status(404).body("merchantStock with id: " + id + " not found.");
    }

    public ResponseEntity deleteMerchantStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++)
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.remove(i);
                return ResponseEntity.status(200).body("merchantStock with id: " + id + " was deleted.");
            }
        return ResponseEntity.status(400).body("merchantStock with id: " + id + " not found.");
    }
}