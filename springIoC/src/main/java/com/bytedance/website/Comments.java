package com.bytedance.website;

public class Comments {
    private String userId;
    private String commentId;
    private String newsId;
    private String commentContent;

    // Constructors, getters, setters, and toString methods
    public Comments(String userId, String commentId, String newsId, String commentContent) {
        this.userId = userId;
        this.commentId = commentId;
        this.newsId = newsId;
        this.commentContent = commentContent;
    }

    public Comments() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "userId='" + userId + '\'' +
                ", commentId='" + commentId + '\'' +
                ", newsId='" + newsId + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
