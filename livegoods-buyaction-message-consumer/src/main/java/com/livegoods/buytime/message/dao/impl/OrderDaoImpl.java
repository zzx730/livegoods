package com.livegoods.buytime.message.dao.impl;

import com.livegoods.buytime.message.dao.OrderDao;
import com.livegoods.commons.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

// 订单数据层实现类
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 保存订单数据
     * @param order
     */
    @Override
    public void save(Order order) {
        mongoTemplate.save(order);
    }
}
