package com.livegoods.commons.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 轮播图实体
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Banner {
    // 主键
    private String id;
    // 图片路径
    private String url;
    // 创建时间
    private Date createTime;
}
