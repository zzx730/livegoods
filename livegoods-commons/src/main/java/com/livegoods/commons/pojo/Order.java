package com.livegoods.commons.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



/**
 * 订单实体
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {
    // 订单id
    private String id;
    // 用户名或手机号
    private String username;
    // 商品主键
    private String itemId;
    // 商品标题
    private String title;
    // 房屋类型
    private String houseType;
    // 价格
    private Long price;
    // 图片
    private String img;
    // 租赁方式
    private String rentType;
    // 评论状态 0：未评论  1：已评论
    private Integer commentState;
}
