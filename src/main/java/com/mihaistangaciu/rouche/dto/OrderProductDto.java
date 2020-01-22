package com.mihaistangaciu.rouche.dto;

public class OrderProductDto {

    private Long sku;
    private Integer quantity;

    public OrderProductDto() {
    }

    public OrderProductDto(Long sku, Integer quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
