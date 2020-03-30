package com.lzq.sharecommunity.service;

import com.lzq.sharecommunity.entity.Comment;
import com.lzq.sharecommunity.repositry.CommentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepositry repositry;

    @Override
    public Comment getById(int id) {
        return repositry.getCommentById(id);
    }

    @Override
    public boolean deleteById(int id) {
        if (repositry.existsById(id)){
            repositry.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Comment addComment(Comment comment) {
        return repositry.save(comment);
    }

    @Override
    public boolean updateComment(Comment comment) {
        if (repositry.existsById(comment.getId())){
            repositry.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public Page<Comment> getByComId(int cId, int pageNum, int PageSize) {
        //按时间排序
        Sort s = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(pageNum, PageSize,s);
        return repositry.getCommentsByTargetId(cId,pageable);
    }

    @Override
    public Page<Comment> getByUserId(int uId, int pageNum, int pageSize) {
        //按时间排序
        Sort s = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(pageNum, pageSize,s);
        return repositry.getCommentsByUserId(uId,pageable);
    }
}
