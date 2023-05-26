package com.livegoods.commons.vo;

import com.livegoods.commons.pojo.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * 返回结果封装类
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LivegoodsResult {
    // 状态码 200： 成功 ， 500： 失败
    private Integer status;
    // 返回的结果
    private Object results;
    // 返回的消息
    private String message;
    // 返回的数据
    private Object data;
    // 分页返回结果，是否还有更多数据
    private Boolean hasMore;
    // 预订时间
    private Long Time;
    public static LivegoodsResult ok(){
        LivegoodsResult livegoodsResult = new LivegoodsResult();
        livegoodsResult.setStatus(200);
        return livegoodsResult;
    }
    public static LivegoodsResult ok(Object data){
        LivegoodsResult livegoodsResult = new LivegoodsResult();
        livegoodsResult.setStatus(200);
        livegoodsResult.setData(data);
        return livegoodsResult;
    }
    public static LivegoodsResult error(){
        LivegoodsResult livegoodsResult = new LivegoodsResult();
        livegoodsResult.setStatus(500);
        return livegoodsResult;
    }

    public static LivegoodsResult error(String message){
        LivegoodsResult livegoodsResult = new LivegoodsResult();
        livegoodsResult.setMessage(message);
        livegoodsResult.setStatus(500);
        return livegoodsResult;
    }

}
