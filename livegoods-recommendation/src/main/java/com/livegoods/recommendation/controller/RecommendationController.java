package com.livegoods.recommendation.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;
    @RequestMapping("recommendation")
    public LivegoodsResult getRecommendation(String city){
        LivegoodsResult recommendation = recommendationService.getRecommendation(city);
        return recommendation;
    }
}
