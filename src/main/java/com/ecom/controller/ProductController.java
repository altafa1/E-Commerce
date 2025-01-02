package com.ecom.controller;

import com.ecom.entity.Product;
import com.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("addproduct/{categoryId}")
    public ResponseEntity<Product>addProduct(@RequestBody Product product, @PathVariable long categoryId){
        Product savedProduct=productService.addProduct(product,categoryId);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        boolean deletedProduct=productService.deleteProduct(productId);
       return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
}
