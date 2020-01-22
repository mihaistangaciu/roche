package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.entites.OrderEntity;
import com.mihaistangaciu.rouche.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<OrderEntity> getAllOrders(Date startDate, Date endDate) {
        return orderRepository.findByDateCreatedBetween(startDate, endDate);
    }

    public OrderEntity create(OrderEntity order) {
        order.setDateCreated(new Date());
        return orderRepository.save(order);
    }

    public OrderEntity update(OrderEntity order) {
        return orderRepository.save(order);
    }


}
