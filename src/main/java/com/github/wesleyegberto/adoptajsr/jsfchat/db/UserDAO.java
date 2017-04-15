package com.github.wesleyegberto.adoptajsr.jsfchat.db;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ApplicationMap;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.UsernameNotUniqueException;
import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@RequestScoped
public class UserDAO {
    
    @Inject
    private Logger LOG;
    
    private final Map<String, Object> USER_DB;
    
    public UserDAO() {
	USER_DB = null;
    }
    
    @Inject
    public UserDAO(@ApplicationMap Map<String, Object> applicationMap) {
	this.USER_DB = Collections.synchronizedMap(applicationMap);
    }
    
    @PostConstruct
    public void postConstruct() {
    }
    
    public boolean isNicknameUsed(String nickname) {
	return USER_DB.containsKey(nickname);
    }
    
    public void createUser(User user) {
	Object prevUser = USER_DB.putIfAbsent(user.getNickname(), user);
	if(prevUser != null && prevUser != user) {
	    throw new UsernameNotUniqueException("Nickname is already in use!");
	}
	LOG.info("User registered: " + user);
    }

    public User getByUsername(String username) {
	return (User) USER_DB.getOrDefault(username, null);
    }
}
