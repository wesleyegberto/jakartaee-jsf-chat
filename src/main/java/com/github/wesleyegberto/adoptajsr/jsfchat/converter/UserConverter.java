package com.github.wesleyegberto.adoptajsr.jsfchat.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.db.UserDAO;
import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@FacesConverter(forClass = User.class, managed = true)
public class UserConverter implements Converter<User> {

    @Inject
    private UserDAO usersDao;
    
    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, User value) {
	System.out.println("[UserConverter] UserDAO injected: " + usersDao);
	
	if(value == null || value.getNickname() == null) return "No user";
	return String.format("%s (%s)", value.getNickname(), value.getName());
    }

}
