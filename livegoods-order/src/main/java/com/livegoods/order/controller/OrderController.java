package com.livegoods.order.controller;

import com.livegoods.commons.pojo.Order;
import com.livegoods.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 根据手机号查询订单集合
     * @param user
     * @return
     */
    @GetMapping("/order")
    public List<Order> getOrders(String user){
        return orderService.getOrders(user);
    }
}
