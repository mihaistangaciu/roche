package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.exception.handler.InvalidProductRequestException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorServiceTest {

    ValidatorService validatorService = new ValidatorService();

    @Test()
    void testValidateNullProduct() {
        Throwable exception = assertThrows(InvalidProductRequestException.class,
                () -> {
                    validatorService.validateProductRequest(null);
                });
    }

    @Test
    void testValidateWrongProduct() {
        Product productWithNotName = new Product();
        productWithNotName.setPrice(new BigDecimal(1));
        Throwable exceptionNoName = assertThrows(InvalidProductRequestException.class,
                () -> {
                    validatorService.validateProductRequest(productWithNotName);
                });

        Product productWithNoPrice = new Product();
        productWithNoPrice.setName("Product with no price");
        Throwable exceptionNoPrice = assertThrows(InvalidProductRequestException.class,
                () -> {
                    validatorService.validateProductRequest(productWithNoPrice);
                });

    }

    @Test()
    void testValidateNullSku() {
        Throwable exception = assertThrows(InvalidProductRequestException.class,
                () -> {
                    validatorService.validateSku(null);
                });
    }


}