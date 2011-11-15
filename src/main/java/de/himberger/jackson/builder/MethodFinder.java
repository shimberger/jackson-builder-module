package de.himberger.jackson.builder;

import java.lang.reflect.Method;

/**
 * Interface to encapsulate method finding.
 */
public interface MethodFinder {

	public Method findInStatic(Class<?> clazz) throws IllegalStateException;
	
	public Method findIn(Class<?> clazz) throws IllegalStateException;
	
}
