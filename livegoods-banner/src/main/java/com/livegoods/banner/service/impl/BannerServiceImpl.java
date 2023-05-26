package com.livegoods.banner.service.impl;

import com.livegoods.banner.dao.BannerDao;
import com.livegoods.banner.service.BannerService;
import com.livegoods.commons.pojo.Banner;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Banner轮播图服务实现类
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    //从配置文件中获取FastDFS服务的ip+port
    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;
    @Override
    public LivegoodsResult getBanners() {
        LivegoodsResult result = new LivegoodsResult();
        try {
            Query query = new Query();
            query.with(PageRequest.of(0,4));
            List<Banner> banners = bannerDao.selectBanners(query);
            result.setStatus(200);
            ArrayList<String> imgList = new ArrayList<>();
            for (Banner banner : banners) {
                //将FastDFS服务器的ip+port与图片的保存路径进行拼接
                imgList.add(nginxPrefix+banner.getUrl());
            }
            result.setResults(imgList);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("轮播图查询失败");
        }
        return result;
    }
}
