package com.mihaistangaciu.rouche.rest;

import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.entites.ProductEntity;
import com.mihaistangaciu.rouche.service.MapperService;
import com.mihaistangaciu.rouche.service.ProductService;
import com.mihaistangaciu.rouche.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rouche/api/products")

public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    private MapperService mapperService;

    private ValidatorService validatorService;

    @Autowired
    public ProductController(ProductService productService, MapperService mapperService, ValidatorService validatorService) {
        this.productService = productService;
        this.mapperService = mapperService;
        this.validatorService = validatorService;
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<Product> createProduct(@RequestBody Product product) {
        validatorService.validateProductRequest(product);
        ProductEntity productEntity = mapperService.convertToProductEntity(product);
        Product result = mapperService.convertToProductFromEntity(productService.createProduct(productEntity));
        logger.trace("Created product: " + productEntity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = {"", "/"})
    public @ResponseBody
    ResponseEntity<List<Product>> retrieveProducts() {
        List<Product> result = mapperService.convertToProductFromEntity(productService.listProducts());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/update")
    public @ResponseBody
    ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        validatorService.validateProductRequest(product);
        validatorService.validateSku(product.getSku());
        ProductEntity productEntity = mapperService.convertToProductEntity(product);
        Product result = mapperService.convertToProductFromEntity(productService.updateProduct(productEntity));
        logger.trace("Updated product: " + productEntity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sku}")
    public @ResponseBody
    ResponseEntity<Product> deleteProduct(@PathVariable Long sku) {
        Product result = mapperService.convertToProductFromEntity(productService.deleteProduct(sku));
        logger.trace("Deleted product with sku: " + sku);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
