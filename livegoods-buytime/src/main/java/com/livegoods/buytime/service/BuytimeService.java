package com.livegoods.buytime.service;

import com.livegoods.commons.vo.LivegoodsResult;

// 商品预定开始时间服务层接口
public interface BuytimeService {
    // 根据商品id查询商品预定时间
    LivegoodsResult getBuyTimeById(String id);
}
