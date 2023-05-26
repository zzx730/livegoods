package com.livegoods.buytime.dao;

import com.livegoods.commons.pojo.Item;
// 商品数据层接口
public interface ItemDao {
    // 根据商品的key值到redis中查询商品的详情
    Item get(String key);
}
