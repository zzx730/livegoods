package com.livegoods.comment.dao.impl;

import com.livegoods.comment.dao.CommentDao;
import com.livegoods.commons.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// 商品评论数据层的实现类
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增商品评论
     * @param comment
     */
    @Override
    public void save(Comment comment) {
        mongoTemplate.save(comment);
    }
    // 分页查询商品评论
    @Override
    public List<Comment> findCommentsByPage(Query query) {
        return mongoTemplate.find(query, Comment.class);
    }

}
