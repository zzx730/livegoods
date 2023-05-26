package com.livegoods.details.service.impl;

import com.livegoods.commons.pojo.Item;
import com.livegoods.details.dao.ItemDao;
import com.livegoods.details.service.DetailsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


// 商品详情服务实现
@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private ItemDao itemDao;
    //从配置文件中获取FastDFS服务的ip+port
    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;
    private static final String BACKEND_B = "backendB";
    /**
     * 根据id查询商品
     * 需要将商品中的图片地址，从相对路径修改为绝对路径
     * @param id
     * @return
     */
    @Override
    @CircuitBreaker(name = BACKEND_B)
    @RateLimiter(name = BACKEND_B)
//    @Cacheable(cacheNames = "com:livegoods:details",key="'getDetails('+#id+')'")
    public Item getDetails(String id) {
        // 根据id查询商品
        Item item = itemDao.findItemById(id);
        // 把图片的相对路径改为绝对路径
        ArrayList<String> imgs = new ArrayList<>();
        for (String img : item.getImgs()) {
            imgs.add(nginxPrefix+img);
        }
        //将图片的绝对路径设置到item中
        item.setImgs(imgs);
        return item;
    }
}
