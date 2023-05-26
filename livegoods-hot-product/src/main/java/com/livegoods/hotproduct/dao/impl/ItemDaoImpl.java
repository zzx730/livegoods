package com.livegoods.hotproduct.dao.impl;

import com.livegoods.commons.pojo.Item;
import com.livegoods.hotproduct.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 热销商品数据访问接口的实现类
 */
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Item> getHotProduct(Query query) {
        List<Item> items = mongoTemplate.find(query, Item.class);
        return items;
    }
}
