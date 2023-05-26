package com.livegoods.details.controller;

import com.livegoods.commons.pojo.Item;
import com.livegoods.commons.pojo.Order;
import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.details.service.DetailsService;
import com.livegoods.details.service.OrderServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DetailsController {
    @Autowired
    private DetailsService detailsService;

    @Autowired(required = false)
    private OrderServiceFeignClient orderServiceFeignClient;

    /**
     * 远程调用订单查询方法
     * @param user
     * @return
     */
    @GetMapping("/details/order")
    public List<Order> selectOrder(String user){
        return orderServiceFeignClient.getOrders(user);
    }

    @GetMapping("/details")
    public Item getDetails(String id){
        return detailsService.getDetails(id);
    }

    @GetMapping("/testDetails")
    public LivegoodsResult testDetails(){
        ArrayList<String> idList = new ArrayList<>();
        idList.add("6468bbeb0334ebcb2e78a51a");
        idList.add("6468bbeb0334ebcb2e78a519");
        idList.add("6468bbeb0334ebcb2e78a518");
        idList.add("6468bbeb0334ebcb2e78a517");
        idList.forEach(s->{
            Item item = detailsService.getDetails(s);
        });
        return LivegoodsResult.ok();
    }
}
