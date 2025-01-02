package com.ecom.service.ServiceImpl;

import com.ecom.entity.ProductCategory;
import com.ecom.reposiroty.ProductCategoryRepository;
import com.ecom.service.CategoryService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;
    @Override
    public ProductCategory addCategory(ProductCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<ProductCategory> addCategories(List<ProductCategory> categories) {
        if(categories==null ||categories.isEmpty()){
            throw new RuntimeException("categories should not be empty or null");
        }
        else
       return  categoryRepository.saveAll(categories);
    }
}
