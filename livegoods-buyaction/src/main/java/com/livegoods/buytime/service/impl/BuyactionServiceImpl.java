package com.livegoods.buytime.service.impl;

import com.livegoods.buytime.dao.ItemDao;
import com.livegoods.buytime.service.BuyactionService;
import com.livegoods.commons.message.LivegoodsBuyMessage;
import com.livegoods.commons.pojo.Item;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

// 预订商品服务层实现类
@Service
public class BuyactionServiceImpl implements BuyactionService {
    @Autowired
    private ItemDao itemDao;

    @Value("${livegoods.cache.names.item.prefix}")
    private String itemPrefix;

    @Value("${livegoods.cache.names.item.suffix}")
    private String itemSuffix;

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public LivegoodsResult buyaction(String id, String user) {
        // 1.访问redis，查询商品详情是否可以预订
        String key = itemPrefix + "::" + itemSuffix + "(" + id + ")";
        Item item = itemDao.get(key);
        if(item.getIsRented()){
            // 房屋已经被预订了
            LivegoodsResult error = LivegoodsResult.error("手慢了，已经被预订了");
            return error;
        }
        // 2.构建一个消息对象，发送消息到mq，并等待消费者响应
        LivegoodsBuyMessage message = new LivegoodsBuyMessage();
        message.setItemId(id);
        message.setUsername(user);
        boolean sendResult = streamBridge.send("livegoodsMessenger-out-0", message);
        // 3.根据消息消费者响应结果，返回操作结果
        if(sendResult){
            // 发送成功，消息消费完成
            LivegoodsResult ok = LivegoodsResult.ok();
            ok.setMessage("预订成功");
            return ok;
        }else{
            LivegoodsResult result = LivegoodsResult.error("手慢了，已经被预订了");
            return result;
        }

    }
}
