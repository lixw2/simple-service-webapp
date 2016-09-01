package com.example.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by li on 2016/8/6.
 */
@XmlRootElement
public class User {
//    @FormParam("userId")
    private String userId;
//    @FormParam("userName")
    private String userName;
//    @FormParam("userAge")
    private int userAge;

    public User() {
    }

    public User(String userId, String userName, int userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }
    //getter&setter


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
