package com.mihaistangaciu.rouche.service;

import com.mihaistangaciu.rouche.entites.ProductEntity;
import com.mihaistangaciu.rouche.exception.handler.ResourceNotFoundException;
import com.mihaistangaciu.rouche.persistence.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    private ProductService productService;
    private ProductEntity productEntity;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService(productRepository);
        productEntity = new ProductEntity("some name", new BigDecimal(12.12));


    }


    @Test
    public void testCreateProduct() {
        when(productRepository.save(any())).thenReturn(productEntity);
        ProductEntity savedEntity = productService.createProduct(productEntity);

        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getCreationDate());
        assertEquals("some name", savedEntity.getName());
        assertEquals(12.12, savedEntity.getPrice().doubleValue(), 1);
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    public void testListProducts() {
        List<ProductEntity> productEntities = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productEntities);

        productEntities.add(productEntity);
        List<ProductEntity> retrievedEntities = productService.listProducts();

        assertNotNull(retrievedEntities);
        assertEquals(1, retrievedEntities.size());
        assertEquals("some name", retrievedEntities.get(0).getName());
        assertEquals(12.12, retrievedEntities.get(0).getPrice().doubleValue(), 1);
        verify(productRepository, times(1)).findAll();

    }

    @Test
    public void testGetProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(productEntity));

        ProductEntity retrievedEntity = productService.getProduct(121L);
        assertNotNull(retrievedEntity);
        assertEquals("some name", retrievedEntity.getName());
        assertEquals(12.12, retrievedEntity.getPrice().doubleValue(), 1);
        verify(productRepository, times(1)).findById(121L);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNotFoundProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        ProductEntity retrievedEntity = productService.getProduct(121L);
        verify(productRepository, times(1)).findById(121L);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNotFoundProductOnDelete() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        ProductEntity retrievedEntity = productService.deleteProduct(121L);
        verify(productRepository, times(1)).findById(121L);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNotFoundProductOnUpdate() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        ProductEntity retrievedEntity = productService.updateProduct(productEntity);
        verify(productRepository, times(1)).findById(121L);

    }

    @Test
    public void testUpdateProduct() {
        ProductEntity productEntityWithUpdatedValue = new ProductEntity();
        productEntityWithUpdatedValue.setName("Updated name");
        productEntityWithUpdatedValue.setPrice(new BigDecimal(121.1));

        when(productRepository.save(any())).thenReturn(productEntity);
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(productEntity));
        ProductEntity savedEntity = productService.updateProduct(productEntityWithUpdatedValue);

        assertNotNull(savedEntity);
        assertEquals("Updated name", savedEntity.getName());
        assertEquals(121.1, savedEntity.getPrice().doubleValue(), 1);
        verify(productRepository, times(1)).save(productEntityWithUpdatedValue);
    }

    @Test
    public void testDeleteProduct() {
        ProductEntity productEntityWithUpdatedValue = new ProductEntity();
        productEntityWithUpdatedValue.setName("Updated name");
        productEntityWithUpdatedValue.setPrice(new BigDecimal(121.1));
        productEntityWithUpdatedValue.setDeleted(true);
        productEntityWithUpdatedValue.setSku(11L);

        when(productRepository.save(productEntity)).thenReturn(productEntityWithUpdatedValue);
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(productEntity));
        ProductEntity savedEntity = productService.deleteProduct(11L);

        assertNotNull(savedEntity);
        assertEquals("Updated name", savedEntity.getName());
        assertEquals(121.1, savedEntity.getPrice().doubleValue(), 1);
        verify(productRepository, times(1)).save(productEntity);
    }


}