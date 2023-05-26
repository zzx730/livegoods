package com.livegoods.buytime.message.dao.impl;

import com.livegoods.buytime.message.dao.ItemDao;
import com.livegoods.commons.pojo.Item;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


// 商品数据层实现类
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 更新商品数据，是否已出租
     * @param id
     * @param rented
     * @return
     */
    @Override
    public long update(String id, Boolean rented) {
        Query query = new Query();
        // 创建查询对象
        query.addCriteria(Criteria.where("id").is(id));
        // 设置更新属性
        Update isRented = Update.update("isRented", rented);
        // 执行更新操作
        UpdateResult updateResult = mongoTemplate.updateFirst(query, isRented, Item.class);
        return updateResult.getModifiedCount();
    }
}
