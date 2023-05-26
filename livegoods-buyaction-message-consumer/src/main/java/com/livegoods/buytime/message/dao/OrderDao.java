package com.livegoods.buytime.message.dao;

import com.livegoods.commons.pojo.Order;

// 订单数据层接口
public interface OrderDao {
    // 保存订单数据
    void save(Order order);
}
