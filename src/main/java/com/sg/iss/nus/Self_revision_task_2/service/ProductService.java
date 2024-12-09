package com.sg.iss.nus.Self_revision_task_2.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.sg.iss.nus.Self_revision_task_2.constant.Constants;
import com.sg.iss.nus.Self_revision_task_2.model.Product;

@Service
public class ProductService {

    @Autowired
    @Qualifier(Constants.template02)
    private RedisTemplate<String, Object> redisTemplate;

    public Map<String, Product> getProductList() {
        Map<Object, Object> productList = redisTemplate.opsForHash().entries(Constants.REDIS_KEY);
        Map<String, Product> products = new HashMap<>();

        for (Map.Entry<Object, Object> entry : productList.entrySet()) {
            String key = entry.getKey().toString(); // Redis key, the title
            Object value = entry.getValue();

            if (value instanceof Map) {
                // Deserialize the HashMap into a Product object
                Map<String, Object> productData = (Map<String, Object>) value;
                Product product = new Product();
                product.setId(Integer.parseInt(productData.get("id").toString()));
                product.setTitle(productData.get("title").toString());
                product.setDescription(productData.get("description").toString());
                product.setPrice(Double.parseDouble(productData.get("price").toString()));
                product.setDiscountPercentage(Double.parseDouble(productData.get("discountPercentage").toString()));
                product.setRating(Double.parseDouble(productData.get("rating").toString()));
                product.setStock(Integer.parseInt(productData.get("stock").toString()));
                product.setBrand(productData.get("brand").toString());
                product.setCategory(productData.get("category").toString());
                product.setDated(Long.parseLong(productData.get("dated").toString()));
                product.setBuyAmount(Integer.parseInt(productData.get("buy").toString()));

                // Add to the final product map
                products.put(key, product);
            }
        }

        return products;
    }

    public boolean buyProduct(String title) {
        Object productObject = redisTemplate.opsForHash().get(Constants.REDIS_KEY, title);

        if (productObject instanceof Map) {
            Map<String, Object> productData = (Map<String, Object>) productObject;

            int currentBuyAmount = Integer.parseInt(productData.get("buy").toString());
            int stockAmount = Integer.parseInt(productData.get("stock").toString());

            if (currentBuyAmount >= stockAmount) {
                return false;
            }

            productData.put("buy", currentBuyAmount + 1);
            redisTemplate.opsForHash().put(Constants.REDIS_KEY, title, productData);

            return true;
        }
        throw new IllegalArgumentException("Product not found in Redis");
    }

    public Product getProductDetails(String title) {

        Object productObject = redisTemplate.opsForHash().get(Constants.REDIS_KEY, title);

        if (productObject instanceof Map) {
            Map<String, Object> productData = (Map<String, Object>) productObject;
            Product product = new Product();
            product.setId(Integer.parseInt(productData.get("id").toString()));
            product.setTitle(productData.get("title").toString());
            product.setDescription(productData.get("description").toString());
            product.setPrice(Double.parseDouble(productData.get("price").toString()));
            product.setDiscountPercentage(Double.parseDouble(productData.get("discountPercentage").toString()));
            product.setRating(Double.parseDouble(productData.get("rating").toString()));
            product.setStock(Integer.parseInt(productData.get("stock").toString()));
            product.setBrand(productData.get("brand").toString());
            product.setCategory(productData.get("category").toString());
            product.setDated(Long.parseLong(productData.get("dated").toString()));
            product.setBuyAmount(Integer.parseInt(productData.get("buy").toString()));

            return product;
        }

        throw new IllegalArgumentException("Product not found in Redis");

    }
}
