package com.livegoods.banner.dao.impl;

import com.livegoods.banner.dao.BannerDao;
import com.livegoods.commons.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BannerDaoImpl implements BannerDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Banner> selectBanners(Query query) {
        List<Banner> banners = mongoTemplate.find(query, Banner.class);
        return banners;
    }
}
