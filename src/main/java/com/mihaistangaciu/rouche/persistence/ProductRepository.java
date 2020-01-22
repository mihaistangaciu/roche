package com.mihaistangaciu.rouche.persistence;

import com.mihaistangaciu.rouche.entites.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
