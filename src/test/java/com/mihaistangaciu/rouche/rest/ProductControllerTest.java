package com.mihaistangaciu.rouche.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihaistangaciu.rouche.dto.Product;
import com.mihaistangaciu.rouche.entites.ProductEntity;
import com.mihaistangaciu.rouche.exception.handler.ApiExceptionHandler;
import com.mihaistangaciu.rouche.service.MapperService;
import com.mihaistangaciu.rouche.service.ProductService;
import com.mihaistangaciu.rouche.service.ValidatorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ProductControllerTest {

    private MockMvc mvc;

    @MockBean
    private ValidatorService validatorService;
    @MockBean
    private ProductService productService;
    @MockBean
    private MapperService mapperService;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ProductController(productService, mapperService, validatorService))
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateProduct() throws Exception {

        Product product = new Product();
        product.setName("name");
        product.setPrice(new BigDecimal(121));
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(product);

        when(validatorService.validateProductRequest(any())).thenReturn(true);
        when(mapperService.convertToProductFromEntity(any(ProductEntity.class))).thenReturn(product);
        when(productService.createProduct(any())).thenReturn(new ProductEntity());
        when(mapperService.convertToProductEntity(any())).thenReturn(new ProductEntity());

        mvc.perform(
                post("/rouche/api/products/create")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.price").value("121"));
        verify(validatorService, times(1)).validateProductRequest(any());
        verify(validatorService, times(0)).validateSku(any());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setName("name");
        product.setPrice(new BigDecimal(121));
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(product);

        when(validatorService.validateProductRequest(any())).thenReturn(true);
        when(validatorService.validateSku(any())).thenReturn(true);
        when(mapperService.convertToProductFromEntity(any(ProductEntity.class))).thenReturn(product);
        when(productService.updateProduct(any())).thenReturn(new ProductEntity());
        when(mapperService.convertToProductEntity(any())).thenReturn(new ProductEntity());

        mvc.perform(
                post("/rouche/api/products/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.price").value("121"));
        verify(validatorService, times(1)).validateProductRequest(any());
        verify(validatorService, times(1)).validateSku(any());

    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product();
        product.setName("name");
        product.setPrice(new BigDecimal(121));
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(product);

        when(mapperService.convertToProductFromEntity(any(ProductEntity.class))).thenReturn(product);
        when(productService.deleteProduct(any())).thenReturn(new ProductEntity());

        mvc.perform(
                delete("/rouche/api/products/delete/{sku}", 1234567)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.price").value("121"));
        verify(validatorService, times(0)).validateProductRequest(any());
        verify(validatorService, times(0)).validateSku(any());
    }

    @Test
    public void testRetrieveProducts() throws Exception {
        Product product = new Product();
        product.setName("name");
        product.setPrice(new BigDecimal(121));
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(mapperService.convertToProductFromEntity(any(List.class))).thenReturn(products);
        when(productService.listProducts()).thenReturn(new ArrayList<ProductEntity>());

        mvc.perform(
                get("/rouche/api/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("name"))
                .andExpect(jsonPath("$[0].price").value("121"));
        verify(validatorService, times(0)).validateProductRequest(any());
        verify(validatorService, times(0)).validateSku(any());
    }
}