package com.example.project3.Controller;
import com.example.project3.Moudle.Merchant;
import com.example.project3.Moudle.MerchantStock;
import com.example.project3.Service.MerchantStockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantstock")
public class MerchantStockController {

    private MerchantStockService merchantStockService;

    public MerchantStockController(MerchantStockService merchantStockService){
        this.merchantStockService = merchantStockService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("MerchantStock id: "+merchantStock.getMerchantId()+" was added.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock merchantStock, @PathVariable String id, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ResponseEntity result = merchantStockService.updateMerchantStock(merchantStock, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        ResponseEntity result = merchantStockService.deleteMerchantStock(id);

        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }
}
