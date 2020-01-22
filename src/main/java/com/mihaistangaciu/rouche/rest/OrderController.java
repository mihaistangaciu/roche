package com.mihaistangaciu.rouche.rest;

import com.mihaistangaciu.rouche.dto.OrderForm;
import com.mihaistangaciu.rouche.dto.OrderProductDto;
import com.mihaistangaciu.rouche.entites.OrderEntity;
import com.mihaistangaciu.rouche.entites.OrderProductEntity;
import com.mihaistangaciu.rouche.service.OrderProductService;
import com.mihaistangaciu.rouche.service.OrderService;
import com.mihaistangaciu.rouche.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("rouche/api/orders")
public class OrderController {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    OrderService orderService;
    OrderProductService orderProductService;
    ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, OrderProductService orderProductService, ProductService productService) {
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.productService = productService;
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<OrderEntity> createOrder(@RequestBody OrderForm orderForm) {

        List<OrderProductDto> orderProductDtos = orderForm.getOrderProduct();

        OrderEntity order = new OrderEntity();

        order.setBuyerEmail(orderForm.getBuyerEmail());
        orderService.create(order);

        List<OrderProductEntity> orderProductsEntity = new ArrayList<>();

        for (OrderProductDto orderProductDto : orderProductDtos) {
            orderProductsEntity.add(orderProductService.create(new OrderProductEntity(order, productService.getProduct(orderProductDto.getSku()), orderProductDto.getQuantity())));
        }

        order.setOrderProducts(orderProductsEntity);

        this.orderService.update(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = {"/{start}/{end}"})
    public @ResponseBody
    ResponseEntity<List<OrderEntity>> retrieveOrders(@PathVariable @DateTimeFormat(pattern = DATE_FORMAT) String start,
                                                     @PathVariable @DateTimeFormat(pattern = DATE_FORMAT) String end) throws ParseException {
        Date startDate = new SimpleDateFormat(DATE_FORMAT).parse(start);
        Date endDate = new SimpleDateFormat(DATE_FORMAT).parse(end);

        List<OrderEntity> result = (List<OrderEntity>) orderService.getAllOrders(startDate, endDate);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
