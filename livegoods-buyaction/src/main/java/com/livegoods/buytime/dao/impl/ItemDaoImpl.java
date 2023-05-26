package com.livegoods.buytime.dao.impl;

import com.livegoods.buytime.dao.ItemDao;
import com.livegoods.commons.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

// 商品数据层实现类
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据商品的key值到redis中查询商品的详情
     * @param key
     * @return
     */
    @Override
    public Item get(String key) {
        Item item = (Item)redisTemplate.opsForValue().get(key);
        return item;
    }
}
