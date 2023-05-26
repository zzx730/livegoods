package com.livegoods.buytime.message.redisdao;

import com.livegoods.commons.pojo.Item;

// 商品redis数据层接口
public interface ItemDao4Redis {
    // 根据key值在redis中查询缓存的商品
    Item get(String key);

    // 根据key值插入value值，如果key重复则覆盖，即覆盖商品的redis数据
    Boolean set(String key,Object value);
}
