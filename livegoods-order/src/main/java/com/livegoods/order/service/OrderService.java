package com.livegoods.order.service;

import com.livegoods.commons.pojo.Order;

import java.util.List;

// 订单服务层接口
public interface OrderService {
    // 根据手机号查询订单数据
    List<Order> getOrders(String user);
}
