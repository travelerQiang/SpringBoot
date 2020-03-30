package com.lzq.sharecommunity.service;

import com.lzq.sharecommunity.entity.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    public Comment getById(int id);

    public boolean deleteById(int id);

    public Comment addComment(Comment comment);

    public boolean updateComment(Comment comment);

    public Page<Comment> getByComId(int cId, int pageNum, int pageSize);

    public Page<Comment> getByUserId(int uId, int pageNum, int pageSize);

}
