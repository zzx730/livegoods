package com.livegoods.comment.controller;

import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 商品评论控制层
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * @param orderId
     * @param comment
     * @return
     */
    @PostMapping("/feelback")
    public LivegoodsResult feelback(String orderId,String comment){
        return commentService.feelback(orderId,comment);
    }

    /**
     * 分页查询商品评论
     * @param itemId
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/comment")
    public LivegoodsResult getCommentsByItemId(@RequestParam(value = "id") String itemId,int page,@RequestParam(defaultValue = "5")int rows){
        return commentService.findCommentsByItemId(itemId,page,rows);
    }
}
