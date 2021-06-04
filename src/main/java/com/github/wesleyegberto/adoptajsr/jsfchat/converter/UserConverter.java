package com.github.wesleyegberto.adoptajsr.jsfchat.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

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
		if (value == null || value.getNickname() == null)
			return "No user";
		return value.getNickname();
	}
}
