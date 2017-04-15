package com.github.wesleyegberto.adoptajsr.jsfchat;

import java.util.Map;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.annotation.ApplicationMap;
import javax.faces.context.FacesContext;

public class JsfArtifactsProducer {

    // @Produces
    @ApplicationMap
    public Map<String, Object> produceApplicationMap(InjectionPoint ip) {
	return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
    }
}
