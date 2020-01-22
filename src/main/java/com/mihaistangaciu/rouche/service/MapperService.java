package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.entites.ProductEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MapperService {

    private ModelMapper modelMapper;

    @Autowired
    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductEntity convertToProductEntity(Product product) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        return productEntity;
    }

    public Product convertToProductFromEntity(ProductEntity productEntity) {
        Product product = modelMapper.map(productEntity, Product.class);
        return product;
    }

    public List<Product> convertToProductFromEntity(List<ProductEntity> productEntities) {
        Type listType = new TypeToken<List<Product>>() {
        }.getType();
        List<Product> products = modelMapper.map(productEntities, listType);
        return products;
    }
}