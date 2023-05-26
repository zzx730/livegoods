package com.livegoods.banner.dao;

import com.livegoods.commons.pojo.Banner;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
// Banner轮播图的数据访问对象，实现数据查询。
public interface BannerDao {
    List<Banner> selectBanners(Query query);
}
