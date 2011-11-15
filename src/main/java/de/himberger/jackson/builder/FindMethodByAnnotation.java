package de.himberger.jackson.builder;

import java.lang.reflect.Method;

/**
 * Class to find a method annotated with specified annotation.
 */
public class FindMethodByAnnotation implements MethodFinder {

	
	private final Class annotationClass;

	public FindMethodByAnnotation(Class annotationClass) {
		super();
		if (annotationClass == null) {
			throw new IllegalArgumentException(Messages.ERR_ANNOTATION_CLASS_NULL);
		}
		this.annotationClass = annotationClass;
	}

	public Class<?> getAnnotationClass() {
		return annotationClass;
	}
	
	public Method findInStatic(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException(Messages.ERR_CLASS_NULL);
		}		
		return find(clazz,clazz.getDeclaredMethods());
	}
	
	public Method findIn(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException(Messages.ERR_CLASS_NULL);
		}		
		return find(clazz,clazz.getMethods());
	}
	
	private Method find(Class<?> clazz,Method[] methods) {
		for (Method method : methods) {
			if (method.getAnnotation(annotationClass) != null) {
				if (method.getParameterTypes().length > 0) {
					// We expect the method to require no arguments
					throw new IllegalStateException(String.format(Messages.ERR_BUILDER_HAS_ARGS,method.getName(),clazz.getSimpleName()));
				}
				return method;
			}
		}
		return null;		
	}
	
}
