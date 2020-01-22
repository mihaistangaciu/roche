package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.entites.OrderProductEntity;
import com.mihaistangaciu.rouche.persistence.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProductService() {
    }

    public OrderProductEntity create(OrderProductEntity orderProductEntity) {
        return orderProductRepository.save(orderProductEntity);
    }
}
