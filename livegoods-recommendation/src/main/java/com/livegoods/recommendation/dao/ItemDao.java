package com.livegoods.recommendation.dao;

import com.livegoods.commons.pojo.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
//商品数据访问接口
public interface ItemDao {
    //查询热门推荐商品
    List<Item> selectRecommendation(Query query);
}
