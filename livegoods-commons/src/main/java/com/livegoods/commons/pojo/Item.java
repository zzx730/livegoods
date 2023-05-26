package com.livegoods.commons.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品实体
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Item {
    // 主键
    private String id;
    // 商品标题
    private String title;
    // 销量
    private Long sales;
    // 是否为推荐商品
    private Boolean recommendation;
    // 商品价格
    private Long price;
    // 所属城市
    private String city;
    // 租赁方式 整租，合租
    private String rentType;
    // 房屋类型
    private String houseType;
    // 商品图片
    private List<String> imgs;

    // 可预订时间
    private Date buytime;
    // 是否已出租
    private Boolean isRented;
    /**
     * 房屋特性， Map集合。集合存储数据内容为： years: "建造年份",type: "房屋类型，几室几厅",
     * level: "所在楼层",style: "装修标准", orientation: "房屋朝向"
     */
    private Map<String, String> info;

    private String houseType4Search;
    public String getHouseType4Search(){
        // 楼层 | 几室几厅 - 面积
        return info.get("level")+" | "+info.get("type")+" - "+houseType;
    }
    //热门排序|权重
    private Byte recoSort;
    private String img;
    public String getImg(){
        return imgs.get(0);
    }

    private String link;
    public String getLink(){
        return "/details/"+id;
    }

}
