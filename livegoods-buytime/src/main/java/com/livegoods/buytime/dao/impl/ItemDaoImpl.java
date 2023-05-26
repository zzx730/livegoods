package com.livegoods.buytime.dao.impl;

import com.livegoods.buytime.dao.ItemDao;
import com.livegoods.commons.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

// 商品数据层接口的实现类
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 根据商品id查询商品
     * @param id
     * @return
     */
    @Override
    public Item findById(String id) {
        return mongoTemplate.findById(id,Item.class);
    }
}
