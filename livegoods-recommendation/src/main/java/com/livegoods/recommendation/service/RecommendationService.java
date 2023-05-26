package com.livegoods.recommendation.service;

import com.livegoods.commons.vo.LivegoodsResult;
// 热门推荐服务接口
public interface RecommendationService {
    /**
     * 查询热门推荐商品信息，查询条件是所在城市
    * @param city 城市
    * @return
    */
    LivegoodsResult getRecommendation(String city);
}
