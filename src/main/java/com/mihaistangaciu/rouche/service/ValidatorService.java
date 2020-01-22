package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.exception.handler.InvalidProductRequestException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidatorService {

    public boolean validateProductRequest(Product product) {
        if (product == null) {
            throw new InvalidProductRequestException("Product details must be specified");
        }
        if (StringUtils.isEmpty(product.getName())) {
            throw new InvalidProductRequestException("Product name must be specified");
        }
        if (StringUtils.isEmpty(product.getPrice())) {
            throw new InvalidProductRequestException("Product price must be specified");
        }
        return true;
    }

    public boolean validateSku(Long sku) {
        if (sku == null) {
            throw new InvalidProductRequestException("Product sku must be specified");
        }

        return true;
    }

}
