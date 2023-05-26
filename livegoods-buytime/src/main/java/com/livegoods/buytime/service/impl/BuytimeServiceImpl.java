package com.livegoods.buytime.service.impl;

import com.livegoods.buytime.dao.ItemDao;
import com.livegoods.buytime.service.BuytimeService;
import com.livegoods.commons.pojo.Item;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 商品预定开始时间服务层接口的实现类
@Service
public class BuytimeServiceImpl implements BuytimeService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public LivegoodsResult getBuyTimeById(String id) {
        Item item = itemDao.findById(id);
        LivegoodsResult result = LivegoodsResult.ok();
        // 设置商品预订开始时间
        result.setTime(item.getBuytime().getTime());
        return result;
    }
}
