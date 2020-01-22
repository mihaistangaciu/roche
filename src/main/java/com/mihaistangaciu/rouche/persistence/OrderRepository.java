package com.mihaistangaciu.rouche.persistence;

import com.mihaistangaciu.rouche.entites.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    Iterable<OrderEntity> findByDateCreatedBetween(Date startDate, Date endDate);
}

