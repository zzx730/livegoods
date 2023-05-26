package com.livegoods.buytime.controller;

import com.livegoods.buytime.service.BuytimeService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuytimeController {

    @Autowired
    private BuytimeService buytimeService;

    /**
     * 获取商品预订开始时间
     * @param id
     * @return
     */
    @GetMapping("/buytime")
    public LivegoodsResult getBuytime(String id){
        return buytimeService.getBuyTimeById(id);
    }
}
