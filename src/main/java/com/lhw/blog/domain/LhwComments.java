package com.lhw.blog.domain;


public class LhwComments {

  private Integer commentId;
  private Integer userId;
  private Integer articleId;
  private Integer commentLikeCount;
  private java.util.Date commentDate;
  private String commentContent;
  private Integer parentCommentId;


  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }


  public Integer getCommentLikeCount() {
    return commentLikeCount;
  }

  public void setCommentLikeCount(Integer commentLikeCount) {
    this.commentLikeCount = commentLikeCount;
  }


  public java.util.Date getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(java.util.Date commentDate) {
    this.commentDate = commentDate;
  }


  public String getCommentContent() {
    return commentContent;
  }

  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }


  public Integer getParentCommentId() {
    return parentCommentId;
  }

  public void setParentCommentId(Integer parentCommentId) {
    this.parentCommentId = parentCommentId;
  }

}
