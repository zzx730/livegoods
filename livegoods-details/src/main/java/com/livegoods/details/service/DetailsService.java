package com.livegoods.details.service;

import com.livegoods.commons.pojo.Item;

// 商品详情服务接口
public interface DetailsService {
    // 根据id查询商品
    Item getDetails(String id);
}
