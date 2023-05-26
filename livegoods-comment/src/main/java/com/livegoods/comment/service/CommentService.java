package com.livegoods.comment.service;

import com.livegoods.commons.vo.LivegoodsResult;
// 商品评论服务接口
public interface CommentService {
    LivegoodsResult feelback(String orderId,String comment);

    //分页查询商品的评论
    LivegoodsResult findCommentsByItemId(String itemId,int page,int rows);
}
