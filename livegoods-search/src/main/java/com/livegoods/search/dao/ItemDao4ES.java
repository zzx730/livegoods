package com.livegoods.search.dao;

import com.livegoods.search.pojo.Item4ES;

import java.util.List;

public interface ItemDao4ES {
    // 批量添加数据到ES
    void batchIndex(List<Item4ES> items);
    // 分页查询
    List<Item4ES> queryForPage(String city, String content, int page, int rows);
}
