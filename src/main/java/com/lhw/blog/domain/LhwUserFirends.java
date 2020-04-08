package com.lhw.blog.domain;


public class LhwUserFirends {

  private Integer id;
  private Integer userId;
  private Integer userFriendsId;
  private String userNote;
  private String userStatus;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getUserFriendsId() {
    return userFriendsId;
  }

  public void setUserFriendsId(Integer userFriendsId) {
    this.userFriendsId = userFriendsId;
  }


  public String getUserNote() {
    return userNote;
  }

  public void setUserNote(String userNote) {
    this.userNote = userNote;
  }


  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

}
