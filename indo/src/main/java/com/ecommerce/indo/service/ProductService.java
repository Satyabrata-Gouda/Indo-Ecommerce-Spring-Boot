package com.ecommerce.indo.service;

import com.ecommerce.indo.model.Product;
import com.ecommerce.indo.model.ProductReq;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductReq req);

    String deleteProduct(Long prodId);

    Product updateProduct(Long prodId, Product product);

    Page<Product> fetchAllProduct(String category,List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

    List<Product> fetchAllByCategory(String category);

    Product fetchProdById(Long prodId);


}
