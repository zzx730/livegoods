package com.livegoods.banner.controller;

import com.livegoods.banner.service.BannerService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 查询轮播图
     * @return
     */
    @RequestMapping("banner")
    public LivegoodsResult banner(){
        return bannerService.getBanners();
    }
}
