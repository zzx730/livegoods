package com.livegoods.order.dao;

import com.livegoods.commons.pojo.Order;

import java.util.List;

// 订单数据层接口
public interface OrderDao {
    // 根据手机号查询订单数据
    List<Order> getOrders(String user);
}
