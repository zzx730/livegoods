package com.livegoods.comment.dao.impl;

import com.livegoods.comment.dao.OrderDao;
import com.livegoods.commons.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
// 订单数据访问层的实现类
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新订单评论状态
     * @param orderId
     * @param commentState
     */
    @Override
    public void updateCommentState(String orderId, int commentState) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));
        Update update = Update.update("commentState", commentState);
        mongoTemplate.updateFirst(query, update, Order.class);
    }
}
