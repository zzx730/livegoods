package com.livegoods.buytime.dao;

import com.livegoods.commons.pojo.Item;

// 商品数据层接口
public interface ItemDao {
    // 根据商品id查询商品
    Item findById(String id);
}
