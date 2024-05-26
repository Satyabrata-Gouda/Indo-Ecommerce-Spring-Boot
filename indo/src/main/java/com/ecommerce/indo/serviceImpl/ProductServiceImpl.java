package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.Category;
import com.ecommerce.indo.model.Product;
import com.ecommerce.indo.model.ProductReq;
import com.ecommerce.indo.repo.CategoryRepo;
import com.ecommerce.indo.repo.ProductRepo;
import com.ecommerce.indo.service.ProductService;
import com.ecommerce.indo.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserService userService;

    @Override
    public Product createProduct(ProductReq req) {
        Product product = new Product();
        try {

            Category topLevel = categoryRepo.findByName(req.getTopLevelCategory());

            if (ObjectUtils.isEmpty(topLevel)) {
                Category topLevelCategory = new Category();
                topLevelCategory.setName(req.getTopLevelCategory());
                topLevelCategory.setLevel(1);

                topLevel = categoryRepo.save(topLevelCategory);
            }

            Category secondLevel = categoryRepo.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());

            if (ObjectUtils.isEmpty(secondLevel)) {
                Category secondLevelCategory = new Category();
                secondLevelCategory.setName(req.getSecondLevelCategory());
                secondLevelCategory.setLevel(2);

                secondLevel = categoryRepo.save(secondLevelCategory);
            }

            Category thirdLevel = categoryRepo.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());

            if (ObjectUtils.isEmpty(thirdLevel)) {
                Category thirdLevelCategory = new Category();
                thirdLevelCategory.setName(req.getThirdLevelCategory());
                thirdLevelCategory.setLevel(3);

                thirdLevel = categoryRepo.save(thirdLevelCategory);
            }


            product = productRepo.save(Product.builder()
                    .title(req.getTitle())
                    .color(req.getColor())
                    .description(req.getDescription())
                    .createdAt(LocalDateTime.now())
                    .brand(req.getBrand())
                    .price(req.getPrice())
                    .imgUrl(req.getImgUrl())
                    .discountPercent(req.getDiscountPercent())
                    .discountedPrice(req.getDiscontedPrice())
                    .sizes(req.getSize())
                    .quantity(req.getQuantity())
                    .category(thirdLevel)
                    .build());


        } catch (Exception e) {

            throw new AuthException(e.getMessage());

        }


        return product;
    }

    @Override
    public String deleteProduct(Long prodId) {

        try {
            Product product = fetchProdById(prodId);

            product.getSizes().clear();
            productRepo.delete(product);

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }


        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long prodId, Product product) {

        Product prod = new Product();

        try {
            prod = fetchProdById(prodId);

            if (product.getQuantity() != 0) {
                prod.setQuantity(product.getQuantity());
            }
            prod = productRepo.save(prod);

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
        return prod;
    }

    @Override
    public Page<Product> fetchAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);

            List<Product> products = productRepo.filterProduct(category, minPrice, maxPrice, minDiscount, sort);

            if (!colors.isEmpty()) {
                products = products.stream().filter(prod -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(prod.getColor()))).collect(Collectors.toList());
            }
            if (StringUtils.isNotBlank(stock)) {
                if (stock.equals("in_stock")) {
                    products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
                } else if (stock.equals("out_of_stock")) {
                    products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
                }
            }

            int startIndex = (int) pageable.getOffset();
            int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

            List<Product> pageContent = products.subList(startIndex, endIndex);

            return new PageImpl<>(pageContent, pageable, products.size());

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }

    }

    @Override
    public List<Product> fetchAllByCategory(String category) {
        return List.of();
    }

    @Override
    public Product fetchProdById(Long prodId) {
        Product prod = new Product();
        try {
            prod = productRepo.findById(prodId).orElseThrow(() -> new AuthException("Product not found with " + prodId));
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
        return prod;
    }
}
