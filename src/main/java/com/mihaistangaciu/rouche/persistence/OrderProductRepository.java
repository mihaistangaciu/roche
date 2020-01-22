package com.mihaistangaciu.rouche.persistence;

import com.mihaistangaciu.rouche.entites.OrderProductEntity;
import com.mihaistangaciu.rouche.entites.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, OrderProductPK> {
}
