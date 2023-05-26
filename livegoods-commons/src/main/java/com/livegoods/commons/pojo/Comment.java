package com.livegoods.commons.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 商品评价实体
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Comment {
    // 商品主键
    private String itemId;
    // 评价的用户名或手机号
    private String username;
    // 评价信息
    private String comment;
    // 评分
    private int star;
}
