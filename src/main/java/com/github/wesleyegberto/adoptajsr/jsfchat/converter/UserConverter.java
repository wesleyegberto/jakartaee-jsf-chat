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
    private UserDAO userDao;
    
    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
	return userDao.getByUsername(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, User value) {
	if(value == null || value.getNickname() == null) return "No user";
	return value.getNickname();
    }
}
