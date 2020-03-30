package com.lzq.sharecommunity.entity;

import javax.persistence.*;
import java.util.Date;

//@JsonIgnoreProperties("hibernateLazyInitializer")//Hibernate获取单个实体类数据后，会为每个实体类添加一个 hibernateLazyInitializer 属性，改属性在进行JSON转换时抛出异常
@Entity
@Table(name = "composition")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "brief")
    private String brief;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "created_date",insertable = false, updatable = false)
    private Date createdDate;

    @Column(name = "c_size")
    private int cSize;

    @Column(name = "c_type")
    private int cType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLike_count(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getcSize() {
        return cSize;
    }

    public void setcSize(int cSize) {
        this.cSize = cSize;
    }

    public int getcType() {
        return cType;
    }

    public void setcType(int cType) {
        this.cType = cType;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", userId=" + userId +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", createdDate=" + createdDate +
                ", cSize=" + cSize +
                ", cType=" + cType +
                '}';
    }
}
