package com.sg.iss.nus.Self_revision_task_2.model;

public class Product {

    private Integer id;

    private String title;

    private String description;

    private Double price;

    private Double discountPercentage;

    private Double rating;

    private Integer stock;

    private String brand;

    private String category;

    private Long dated;

    private Integer buyAmount;

    public Product() {
    }

    public Product(Integer id, String title, String description, Double price, Double discountPercentage, Double rating,
            Integer stock, String brand, String category, Long dated, Integer buyAmount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.dated = dated;
        this.buyAmount = buyAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getDated() {
        return dated;
    }

    public void setDated(Long dated) {
        this.dated = dated;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + description + "," + price
                + "," + discountPercentage + "," + rating + "," + stock + ","
                + brand + "," + category + "," + dated + "," + buyAmount;
    }

}
