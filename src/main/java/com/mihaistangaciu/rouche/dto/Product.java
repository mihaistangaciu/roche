package com.mihaistangaciu.rouche.dto;

import java.math.BigDecimal;

public class Product {

    private Long sku;
    private String name;
    private BigDecimal price;

    public Product() {
    }

    public Product(Long sku, String name, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
