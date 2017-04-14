package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.annotation.ApplicationMap;
import javax.faces.annotation.ApplicationMap;
import javax.faces.annotation.SessionMap;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.db.UserDAO;
import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@Model
public class IndexController {

    // throwing WELD-001408: Unsatisfied dependencies
    // @Inject @SessionMap
    // private Map<String, Object> map;
    
    private User user;
    
    // @Inject
    private UserDAO userDao;

    @Inject @Any
    private Instance<Map<?,?>> rawMap;


    @Inject @Any
    private Instance<Map<String, Object>> typedMap;
    
    @PostConstruct
    public void init() {
	System.out.println("[IndexController] postConstruct");
	System.out.println("[IndexController] UserDAO injected: " + userDao);

	System.out.printf("[IndexController] Raw Map resolvable: %b, ambigous: %b, unsatisfied: %b\n",
		rawMap.isResolvable(), rawMap.isAmbiguous(), rawMap.isUnsatisfied());
	
	System.out.printf("[IndexController] Typed Map resolvable: %b, ambigous: %b, unsatisfied: %b\n",
		typedMap.isResolvable(), typedMap.isAmbiguous(), typedMap.isUnsatisfied());

	Instance<Map<?, ?>> qualifRawMap = rawMap.select(new AnnotationLiteral<ApplicationMap>() {});
	System.out.printf("[IndexController] Raw Map Qualif resolvable: %b, ambigous: %b, unsatisfied: %b\n",
		qualifRawMap.isResolvable(), qualifRawMap.isAmbiguous(), qualifRawMap.isUnsatisfied());

	Instance<Map<String, Object>> typedMapQualf = typedMap.select(new AnnotationLiteral<ApplicationMap>() {});
	System.out.printf("[IndexController] Type Map Qualif resolvable: %b, ambigous: %b, unsatisfied: %b\n",
		typedMapQualf.isResolvable(), typedMapQualf.isAmbiguous(), typedMapQualf.isUnsatisfied());
    }
    
    public User getUser() {
	if (user == null)
	    user = new User();
	return user;
    }

    public String getMessage() {
	return "Hello JSF 2.3!";
    }
    
    public String signIn() {
	System.out.println(user);
	return null;
    }
}
