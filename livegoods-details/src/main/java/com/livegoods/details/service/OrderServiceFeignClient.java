package com.livegoods.details.service;

import com.livegoods.commons.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "livegoods-order")
public interface OrderServiceFeignClient {
    /**
     * 远程调用order模块的获取订单集合方法
     * @param user
     * @return
     */
    @GetMapping("/order")
    public List<Order> getOrders(@RequestParam("user") String user);
}
