package com.livegoods.buytime.message.listener;

import com.livegoods.buytime.message.service.BuyactionService;
import com.livegoods.commons.message.LivegoodsBuyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class LivegoodsBuyactionMessageConsumer {
    @Autowired
    private BuyactionService buyactionService;

    /**
     * 消息消费者方法
     * @return
     */
    @Bean
    public Consumer<LivegoodsBuyMessage> livegoodsMessenger(){
        return message->{
            // 商品id
            String itemId = message.getItemId();
            // 预订商品的用户名
            String username = message.getUsername();
            Boolean result = buyactionService.buyaction(itemId, username);
            log.info("消息消费结果："+result);
        };
    }
}
