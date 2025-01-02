package com.ecom.controller;

import com.ecom.entity.ProductCategory;
import com.ecom.service.CategoryService;
import com.ecom.service.ServiceImpl.CategoryServiceImpl;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;



    @PostMapping("/add")
    public ResponseEntity<ProductCategory> addCategory(@RequestBody ProductCategory category){

        ProductCategory savedCategory= categoryService.addCategory(category);
      return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
  }
  @PostMapping("/addlist")
    public ResponseEntity<List<ProductCategory>>addCategoryList(@RequestBody  List<ProductCategory> categories){
        List<ProductCategory> savedCategories=categoryService.addCategories(categories);
        return new ResponseEntity<>(savedCategories,HttpStatus.CREATED);
  }

}
