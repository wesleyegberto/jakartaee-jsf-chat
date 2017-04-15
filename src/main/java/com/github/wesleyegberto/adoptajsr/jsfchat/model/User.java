package com.github.wesleyegberto.adoptajsr.jsfchat.model;

/**
 * Chat User.
 * 
 * @author wesley
 */
public class User {
    private String nickname;
    private String name;

    public User() {
    }
    
    public User(String nickname, String name) {
	this.nickname = nickname;
	this.name = name;
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "User [nickname=" + nickname + ", name=" + name + "]";
    }

}
