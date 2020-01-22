package com.mihaistangaciu.rouche.integration;

import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.entites.ProductEntity;
import com.mihaistangaciu.rouche.persistence.ProductRepository;
import com.mihaistangaciu.rouche.rest.OrderController;
import com.mihaistangaciu.rouche.rest.ProductController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

    @Autowired
    private ProductController productController;
    @Autowired
    private OrderController orderController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    ProductRepository productRepository;
    @LocalServerPort
    private int port;

    @Test
    public void loadApplicationContext() {
        Assertions
                .assertThat(productController)
                .isNotNull();
        Assertions
                .assertThat(orderController)
                .isNotNull();
    }

    @Test
    public void testCreateProduct() {
        Product requestProduct = new Product();
        requestProduct.setPrice(new BigDecimal(121.1));
        requestProduct.setName("My product");
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "rouche/api/products/create", requestProduct, Product.class);
        Product product = responseEntity.getBody();
        Long sku = product.getSku();

        ProductEntity productEntity = productRepository.findById(sku).get();

        assertNotNull(product);
        assertEquals("My product", product.getName());
        assertEquals(121.1, product.getPrice().doubleValue(), 1);
        assertEquals(product.getName(), productEntity.getName());
        assertEquals(product.getPrice().doubleValue(), productEntity.getPrice().doubleValue(),1);

    }

}
