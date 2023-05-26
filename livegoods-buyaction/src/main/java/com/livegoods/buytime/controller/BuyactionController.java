package com.livegoods.buytime.controller;

import com.livegoods.buytime.service.BuyactionService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyactionController {
    @Autowired
    private BuyactionService buyactionService;

    /**
     * 预订商品
     * @param id
     * @param user
     * @return
     */
    @GetMapping("/buyaction")
    public LivegoodsResult buyaction(String id,String user){
        return buyactionService.buyaction(id,user);

    }
}
