package com.example.project3.Service;

import com.example.project3.Moudle.Merchant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAllMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public ResponseEntity updateMerchant(Merchant merchant, String id) {
        for (int i = 0; i < merchants.size(); i++)
            if (merchants.get(i).getId().equals(id)) {
                merchants.set(i, merchant);
                return ResponseEntity.status(200).body("merchant with id: " + id + " was updated.");
            }
        return ResponseEntity.status(404).body("merchant with id: " + id + " not found.");
    }

    public ResponseEntity deleteMerchant(String id) {
        for (int i = 0; i < merchants.size(); i++)
            if (merchants.get(i).getId().equals(id)) {
                merchants.remove(i);
                return ResponseEntity.status(200).body("\"merchant with id: \"+id+\" was deleted.\"");
            }
        return ResponseEntity.status(400).body("merchant with id: " + id + " not found.");
    }
}