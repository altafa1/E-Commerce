package com.ecom.service;

import com.ecom.entity.ProductCategory;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    ProductCategory addCategory(ProductCategory category) ;

    List<ProductCategory> addCategories(List<ProductCategory> categories);
}
