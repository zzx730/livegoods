package com.livegoods.search.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 搜索商品
     * @param city
     * @param content 搜索的内容
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public LivegoodsResult search(String city, String content, int page, @RequestParam(defaultValue = "4") int rows){
        LivegoodsResult search = searchService.search(city, content, page, rows);
        return search;

    }
}
