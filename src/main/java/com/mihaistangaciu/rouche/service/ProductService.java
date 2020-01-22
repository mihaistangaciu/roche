package com.mihaistangaciu.rouche.service;


import com.mihaistangaciu.rouche.entites.ProductEntity;
import com.mihaistangaciu.rouche.exception.handler.ResourceNotFoundException;
import com.mihaistangaciu.rouche.persistence.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity createProduct(ProductEntity product) {
        product.setCreationDate(new Date());
        return productRepository.save(product);
    }

    public List<ProductEntity> listProducts() {
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepository.findAll();
        return productEntities;
    }

    public ProductEntity getProduct(Long sku) {
        Optional<ProductEntity> productEntity = productRepository.findById(sku);
        if (productEntity.isPresent()) {
            return productEntity.get();
        } else {
            logger.debug("Product sku requested doesn't exist: " + sku);
            throw new ResourceNotFoundException("Product doesn't exist");
        }
    }

    public ProductEntity updateProduct(ProductEntity product) {
        Optional<ProductEntity> productEntity = productRepository.findById(product.getSku());
        if (productEntity.isPresent()) {
            productEntity.get().setName(product.getName());
            productEntity.get().setPrice(product.getPrice());
            return productRepository.save(productEntity.get());
        } else {
            logger.debug("Attempt to update non existing product: " + product);
            throw new ResourceNotFoundException("Product can't be updated because doesn't exist");
        }
    }

    public ProductEntity deleteProduct(Long sku) {
        Optional<ProductEntity> productEntity = productRepository.findById(sku);
        if (productEntity.isPresent()) {
            productEntity.get().setDeleted(true);
            return productRepository.save(productEntity.get());
        } else {
            logger.debug("Attempt to delete non existing product qith sku: " + sku);
            throw new ResourceNotFoundException("Product can't be deleted because doesn't exist");
        }
    }

}
