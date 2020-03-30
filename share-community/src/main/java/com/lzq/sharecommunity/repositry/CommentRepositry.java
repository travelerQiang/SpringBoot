package com.lzq.sharecommunity.repositry;

import com.lzq.sharecommunity.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositry extends JpaRepository<Comment,Integer> {

    public Comment getCommentById(int id);

    public Page<Comment> getCommentsByTargetId(int targetId, Pageable pageable);

    public Page<Comment> getCommentsByUserId(int userId, Pageable pageable);
}
