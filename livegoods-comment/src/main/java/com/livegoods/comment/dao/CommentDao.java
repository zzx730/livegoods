package com.livegoods.comment.dao;

import com.livegoods.commons.pojo.Comment;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

//商品评论数据访问接口
public interface CommentDao {
    //新增评论
    void save(Comment comment);
    // 分页查询评论数据
    List<Comment> findCommentsByPage(Query query);

}
