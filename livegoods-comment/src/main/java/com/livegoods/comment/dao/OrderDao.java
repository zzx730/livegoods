package com.livegoods.comment.dao;

// 订单数据访问接口
public interface OrderDao {
   void updateCommentState(String orderId,int commentState);
}
