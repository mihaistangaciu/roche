package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.entites.ProductEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperServiceTest {

    private ModelMapper modelMapper = new ModelMapper();
    private MapperService mapperService = new MapperService(modelMapper);


    @Test
    void testConvertProductToEntity() {
        Product product = new Product();
        product.setName("Product Name");
        product.setPrice(new BigDecimal(100.1));

        ProductEntity productEntity = mapperService.convertToProductEntity(product);

        assertNotNull(productEntity);
        assertEquals("Product Name", productEntity.getName());
        assertEquals(new BigDecimal(100.1), productEntity.getPrice());
        assertNull(productEntity.getSku());
        assertNull(productEntity.getCreationDate());
    }

    @Test
    void testConvertEntityToProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Product Name");
        productEntity.setPrice(new BigDecimal(100.1));
        productEntity.setSku(1211L);
        productEntity.setDeleted(false);

        Product product = mapperService.convertToProductFromEntity(productEntity);

        assertNotNull(product);
        assertEquals("Product Name", product.getName());
        assertEquals(new BigDecimal(100.1), product.getPrice());
        assertEquals(1211L, product.getSku());

    }

    @Test
    void testConvertEntityListToProducts() {
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setName("Product Name1");
        productEntity1.setPrice(new BigDecimal(100.1));
        productEntity1.setSku(1L);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setName("Product Name2");
        productEntity2.setPrice(new BigDecimal(102.1));
        productEntity2.setSku(2L);

        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity1);
        productEntities.add(productEntity2);

        List<Product> products = mapperService.convertToProductFromEntity(productEntities);

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product Name1", products.get(0).getName());
        assertEquals(new BigDecimal(100.1), products.get(0).getPrice());
        assertEquals(1L, products.get(0).getSku());
        assertEquals("Product Name2", products.get(1).getName());
        assertEquals(new BigDecimal(102.1), products.get(1).getPrice());
        assertEquals(2L, products.get(1).getSku());

    }
}