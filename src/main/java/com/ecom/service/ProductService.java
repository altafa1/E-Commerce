package com.ecom.service;

import com.ecom.entity.Product;

public interface ProductService {
    Product addProduct(Product product,long categoryId);

    boolean deleteProduct(long productId);
}
