package com.livegoods.buytime.message.service.impl;

import com.livegoods.buytime.message.dao.ItemDao;
import com.livegoods.buytime.message.dao.OrderDao;
import com.livegoods.buytime.message.redisdao.ItemDao4Redis;
import com.livegoods.buytime.message.service.BuyactionService;
import com.livegoods.commons.pojo.Item;
import com.livegoods.commons.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
// 预订商品服务层接口的实现类
@Service
public class BuyactionServiceImpl implements BuyactionService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ItemDao4Redis itemDao4Redis;

    @Value("${livegoods.cache.names.item.prefix}")
    private String itemPrefix;

    @Value("${livegoods.cache.names.item.suffix}")
    private String itemSuffix;

    /**
     * 预订商品,修改redis的对应商品信息，以及保存订单信息到MongoDB
     * @param id 商品主键
     * @param user 用户手机号
     * @return
     */
    @Override
    public Boolean buyaction(String id, String user) {
        // 1.根据key从redis中获取商品信息
        String key = itemPrefix + "::" + itemSuffix + "(" + id + ")";
        Item item = itemDao4Redis.get(key);
        // 2.设置商品对象的isRented属性
        item.setIsRented(true);//设置为已出租
        // 3.更新商品数据到redis中
        Boolean isUpdate = itemDao4Redis.set(key, item);
        // 4.更新成功则生成order订单数据
        if(isUpdate){
            long rows = itemDao.update(id,true);
            if(rows==1){
                Order order = new Order();
                order.setCommentState(0);
                order.setHouseType(item.getHouseType4Search());
                order.setImg(item.getImg());
                order.setPrice(item.getPrice());
                order.setUsername(user);
                order.setItemId(item.getId());
                order.setRentType(item.getRentType());
                order.setTitle(item.getTitle());
                // 5.保存订单
                orderDao.save(order);
                return true;
            }
            return false;

        }else {
            return false;
        }
    }
}
