package com.livegoods.order.service.impl;

import com.livegoods.commons.pojo.Order;
import com.livegoods.order.dao.OrderDao;
import com.livegoods.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 订单服务层接口的实现类
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据手机号查询订单数据
     * @param user
     * @return
     */
    @Override
    public List<Order> getOrders(String user) {
        List<Order> orders = orderDao.getOrders(user);
        return orders;
    }
}
