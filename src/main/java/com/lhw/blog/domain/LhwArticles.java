package com.lhw.blog.domain;


public class LhwArticles {

  private Integer articleId;
  private Integer userId;
  private String articleTitle;
  private String articleContent;
  private Integer articleViews;
  private Integer articleCommentCount;
  private java.util.Date articleDate;
  private Integer articleLikeCount;


  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getArticleTitle() {
    return articleTitle;
  }

  public void setArticleTitle(String articleTitle) {
    this.articleTitle = articleTitle;
  }


  public String getArticleContent() {
    return articleContent;
  }

  public void setArticleContent(String articleContent) {
    this.articleContent = articleContent;
  }


  public Integer getArticleViews() {
    return articleViews;
  }

  public void setArticleViews(Integer articleViews) {
    this.articleViews = articleViews;
  }


  public Integer getArticleCommentCount() {
    return articleCommentCount;
  }

  public void setArticleCommentCount(Integer articleCommentCount) {
    this.articleCommentCount = articleCommentCount;
  }


  public java.util.Date getArticleDate() {
    return articleDate;
  }

  public void setArticleDate(java.util.Date articleDate) {
    this.articleDate = articleDate;
  }


  public Integer getArticleLikeCount() {
    return articleLikeCount;
  }

  public void setArticleLikeCount(Integer articleLikeCount) {
    this.articleLikeCount = articleLikeCount;
  }

}
