package com.livegoods.buytime.message.dao;

// 商品数据层接口
public interface ItemDao {
    //更新商品数据，是否已出租
    long update(String id,Boolean rented);
}
