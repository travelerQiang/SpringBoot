package com.lzq.sharecommunity.controller;

import com.lzq.sharecommunity.entity.Comment;
import com.lzq.sharecommunity.service.CommentService;
import com.lzq.sharecommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

//    @ResponseBody
//    @PostMapping("/save")
//    public String saveComment(Comment comment, HttpServletRequest request){
//        comment.setId(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
//        Comment comment1 = commentService.addComment(comment);
//    }
//
    @ResponseBody
    @GetMapping("/article/{id}")
    public Page<Comment> showComments(@PathVariable("id") int id, HttpServletRequest request){
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Page<Comment> comments = commentService.getByComId(id, pageNum, 2);
        return comments;
    }

    @ResponseBody
    @PostMapping("/save")
    public Comment saveComment(Comment comment, HttpServletRequest request){
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        String userHead = request.getSession().getAttribute("userHead").toString();
        String username = request.getSession().getAttribute("username").toString();
        System.out.println(request.getParameter("targetId"));
        int targetId = comment.getTargetId();
        int type = comment.getType();
        String content = comment.getContent();
        Comment c = new Comment();
        c.setContent(content);
        c.setTargetId(targetId);
        c.setType(type);
        c.setUserHead(userHead);
        c.setUserId(userId);
        c.setUsername(username);
        Comment comment1 = commentService.addComment(c);
        return  comment1;
    }
}
