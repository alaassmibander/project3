package com.example.project3.Controller;

import com.example.project3.Moudle.Merchant;
import com.example.project3.Moudle.User;
import com.example.project3.Service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllMerchants() {
        return ResponseEntity.status(200).body(merchantService.getAllMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant: " + merchant.getName() + " was added.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merchant, @PathVariable String id, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ResponseEntity result = merchantService.updateMerchant(merchant, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        ResponseEntity result = merchantService.deleteMerchant(id);

        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }

}