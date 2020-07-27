package com.lhw.blog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class LhwUser {

  private Integer userId;
  private String userIp;
  private String userName;
  @JsonIgnore
  private String userPassword;
  private String userEmail;
  private String userProfilePhoto;
  private java.util.Date userRegistrationTime;
  private java.sql.Date userBirthday;
  private Integer userAge;
  private String userTelephoneNumber;
  private String userNickname;
  private String userSignature;

  public String getUserSignature() {
    return userSignature;
  }

  public void setUserSignature(String userSignature) {
    this.userSignature = userSignature;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public String getUserProfilePhoto() {
    return userProfilePhoto;
  }

  public void setUserProfilePhoto(String userProfilePhoto) {
    this.userProfilePhoto = userProfilePhoto;
  }


  public java.util.Date getUserRegistrationTime() {
    return userRegistrationTime;
  }

  public void setUserRegistrationTime(java.util.Date userRegistrationTime) {
    this.userRegistrationTime = userRegistrationTime;
  }


  public java.sql.Date getUserBirthday() {
    return userBirthday;
  }

  public void setUserBirthday(java.sql.Date userBirthday) {
    this.userBirthday = userBirthday;
  }


  public Integer getUserAge() {
    return userAge;
  }

  public void setUserAge(Integer userAge) {
    this.userAge = userAge;
  }


  public String getUserTelephoneNumber() {
    return userTelephoneNumber;
  }

  public void setUserTelephoneNumber(String userTelephoneNumber) {
    this.userTelephoneNumber = userTelephoneNumber;
  }


  public String getUserNickname() {
    return userNickname;
  }

  public void setUserNickname(String userNickname) {
    this.userNickname = userNickname;
  }

}
