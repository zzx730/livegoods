package com.livegoods.buytime.service;

import com.livegoods.commons.vo.LivegoodsResult;

// 预订商品服务层接口
public interface BuyactionService {
    /**
     * 预订商品服务方法
     * @param id
     * @param user
     * @return
     */
    LivegoodsResult buyaction(String id,String user);
}
