package com.livegoods.buytime.message.service;
// 预订商品服务层接口
public interface BuyactionService {

    /**
     * 预订商品
     * @param id 商品主键
     * @param user 用户手机号
     * @return
     */
    Boolean buyaction(String id,String user);

}
