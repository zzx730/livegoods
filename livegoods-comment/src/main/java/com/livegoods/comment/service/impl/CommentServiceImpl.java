package com.livegoods.comment.service.impl;

import com.livegoods.comment.dao.CommentDao;
import com.livegoods.comment.dao.OrderDao;
import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.pojo.Comment;
import com.livegoods.commons.pojo.Order;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

// 商品评论服务层的实现类
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 新增商品评论
     * @param orderId 订单主键
     * @param comment 评论内容
     * @return
     */
    @Override
    public LivegoodsResult feelback(String orderId, String comment) {
        try{
            // 根据订单id查询订单数据
            // 未开发Order订单模块，先进行注释
//            Order order = orderDao.findById(orderId);
            // 未开发Order订单模块，先使用模拟数据进行测试，后面删除
            Order order = new Order();
            order.setUsername("13719244228");
            order.setItemId("6468bbeb0334ebcb2e78a51a");

            // 创建评论对象
            Comment commentObject = new Comment();
            commentObject.setUsername(order.getUsername());
            commentObject.setComment(comment);
            commentObject.setItemId(order.getItemId());
            commentObject.setStar(3);
            // 添加评论
            commentDao.save(commentObject);

            //更新订单里面的评论状态 设置为已评论
            orderDao.updateCommentState(orderId,1);
            return LivegoodsResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return LivegoodsResult.error();
        }
    }

    /**
     * 分页查询商品评论
     * @param itemId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public LivegoodsResult findCommentsByItemId(String itemId, int page, int rows) {
        Query query = new Query();
        query.addCriteria(Criteria.where("itemId").is(itemId));
        query.with(PageRequest.of(page*rows,rows));
        // 分页查询
        List<Comment> commentsByPage = commentDao.findCommentsByPage(query);
        // 评论数
        long count = commentsByPage==null?0:commentsByPage.size();
        for (Comment comment : commentsByPage) {
            String username = comment.getUsername().replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$2");
            comment.setUsername(username);
        }
        LivegoodsResult result = LivegoodsResult.ok(commentsByPage);
        // 计算总页数
        long totalPages = (count % rows == 0) ? (count / rows) : (count / rows + 1);
        // 判断是否还有更多数据
        if((page+1)<totalPages){
            result.setHasMore(true);
        }else {
            result.setHasMore(false);
        }
        return result;
    }
}
