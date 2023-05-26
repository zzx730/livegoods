package com.livegoods.commons.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class LivegoodsBuyMessage implements Serializable {
    // 商品id
    private String itemId;
    // 预订商品的用户名
    private String username;

}
