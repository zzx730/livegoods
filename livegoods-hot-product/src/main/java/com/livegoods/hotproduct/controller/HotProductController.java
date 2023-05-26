package com.livegoods.hotproduct.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.hotproduct.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotProductController{
    @Autowired
    private HotProductService hotProductService;

    @GetMapping("hotProduct")
    public LivegoodsResult getHotProduct(String city){
        LivegoodsResult hotProduct = hotProductService.getHotProduct(city);
        return hotProduct;
    }

}
