package com.ecom.service.ServiceImpl;

import com.ecom.entity.Product;
import com.ecom.entity.ProductCategory;
import com.ecom.reposiroty.ProductCategoryRepository;
import com.ecom.reposiroty.ProductRepository;
import com.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;
    @Override
    public Product addProduct(Product product,long categoryId) {
        if(product==null ){
            throw new RuntimeException("product should not be null");
        }
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("category not available")
        );
        product.setProductCategory(category);
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("product not present")
        );
        if(product!=null) {
            productRepository.deleteById(productId);
            return true;
        }
        else
            return false;
    }
}
