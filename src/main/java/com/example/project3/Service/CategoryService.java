package com.example.project3.Service;

import com.example.Service.model.Category;
import com.example.Service.model.Merchant;
import com.example.Service.utility.ServiceResponse;
import com.example.project3.Moudle.Category;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAllCategories(){
        return categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public ResponseEntity updateCategory(@Valid Category category, String id){
        for (int i = 0; i < categories.size(); i++)
            if(categories.get(i).getId().equals(id)) {
                categories.set(i, category);
                return new ServiceResponse(200, "category with id: "+id+" was updated.");
            }
        return new ServiceResponse(404, "category with id: "+id+" not found.");
    }

    public ResponseEntity deleteCategory(String id){
        for (int i = 0; i < categories.size(); i++)
            if(categories.get(i).getId().equals(id)) {
                categories.remove(i);
                return new ServiceResponse(200, "category with id: "+id+" was deleted.");
            }
        return new ServiceResponse(400, "category with id: "+id+" not found.");
    }
}