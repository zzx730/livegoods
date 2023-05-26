package com.livegoods;

import com.livegoods.commons.pojo.Item;
import com.livegoods.search.SearchApplication;
import com.livegoods.search.dao.ItemDao4ES;
import com.livegoods.search.pojo.Item4ES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class TestSearch {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ItemDao4ES itemDao4ES;
    //从配置文件中获取FastDFS服务的ip+port
    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;
    @Test
    public void testInitES(){
        List<Item> items = mongoTemplate.findAll(Item.class);
        ArrayList<Item4ES> list = new ArrayList<>();
        items.forEach(item4ES -> {
            Item4ES item = new Item4ES();
            item.setId(item4ES.getId());
            item.setCity(item4ES.getCity());
            item.setHouseType(item4ES.getHouseType());
            item.setImg(nginxPrefix+item4ES.getImg());
            item.setPrice(String.valueOf(item4ES.getPrice()));
            item.setTitle(item4ES.getTitle());
            item.setRentType(item4ES.getRentType());
            list.add(item);
        });
        itemDao4ES.batchIndex(list);
    }
}
