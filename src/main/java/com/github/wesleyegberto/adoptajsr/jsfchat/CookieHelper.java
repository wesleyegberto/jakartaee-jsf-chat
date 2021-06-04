package com.github.wesleyegberto.adoptajsr.jsfchat;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.RequestCookieMap;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.Cookie;

@RequestScoped
public class CookieHelper {

	@Inject
	@RequestCookieMap
	private Map<String, Object> cookies;

	@Inject
	private ExternalContext externalContext;

	public CookieHelper() {
	}

	public boolean containsCookie(String cookieName) {
		return cookies.containsKey(cookieName);
	}

	public String getCookieValue(String cookieName) {
		Object cookieValue = cookies.get(cookieName);
		if (cookieValue != null && cookieValue instanceof Cookie) {
			Cookie cookie = (Cookie) cookieValue;
			try {
				return new String(Base64.getDecoder().decode(cookie.getValue()), "utf-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return null;
	}

	public void setCookie(String cookieName, String cookieValue) {
		String encodedValue = null;
		try {
			encodedValue = Base64.getEncoder().encodeToString(cookieValue.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		Map<String, Object> props = new LinkedHashMap<>();
		props.put("httpOnly", Boolean.TRUE);
		externalContext.addResponseCookie(cookieName, encodedValue, props);
	}
}
