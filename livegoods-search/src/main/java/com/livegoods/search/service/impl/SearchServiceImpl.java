package com.livegoods.search.service.impl;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.search.dao.ItemDao4ES;
import com.livegoods.search.pojo.Item4ES;
import com.livegoods.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 搜索服务的实现类
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ItemDao4ES itemDao4ES;

    /**
     * 搜索商品逻辑
     * @param city 城市
     * @param content 搜索关键字
     * @param page 第几页， 从0开始
     * @param rows 每页查询多少行
     * @return
     */
    @Override
    public LivegoodsResult search(String city, String content, int page, int rows) {
        List<Item4ES> item4ES = itemDao4ES.queryForPage(city, content, page, rows);
        return LivegoodsResult.ok(item4ES);
    }
}
