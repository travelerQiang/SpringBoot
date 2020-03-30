package com.lzq.sharecommunity.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "target_id")
    private int targetId;

    @Column(name = "created_date")
    private Date  createdDate;

    @Column(name = "type")
    private int type;

    @Column(name = "username")
    private String username;

    @Column(name = "user_id")
    private int userId;

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    @Column(name = "user_head")
    private String userHead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", targetId=" + targetId +
                ", createdDate=" + createdDate +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }
}
