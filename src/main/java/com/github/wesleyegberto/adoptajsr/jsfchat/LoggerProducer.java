package com.github.wesleyegberto.adoptajsr.jsfchat;

import java.util.logging.Logger;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
	@Produces
	public Logger produceLogger(InjectionPoint ip) {
		Class<?> clazz = ip.getMember().getDeclaringClass();
		return Logger.getLogger(clazz.getName());
	}
}
