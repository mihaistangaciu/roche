package com.mihaistangaciu.rouche.dto;

import java.util.List;

public class OrderForm {

    private List<OrderProductDto> orderProduct;
    private String buyerEmail;

    public OrderForm() {
    }

    public OrderForm(List<OrderProductDto> orderProduct, String buyerEmail) {
        this.orderProduct = orderProduct;
        this.buyerEmail = buyerEmail;
    }

    public List<OrderProductDto> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(List<OrderProductDto> orderProductDtos) {
        this.orderProduct = orderProductDtos;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
}
