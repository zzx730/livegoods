package com.livegoods.order.dao.impl;

import com.livegoods.commons.pojo.Order;
import com.livegoods.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
// 订单数据层接口的实现类
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据手机号查询订单数据
     * @param user 手机号
     * @return
     */
    @Override
    public List<Order> getOrders(String user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user));
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }
}
