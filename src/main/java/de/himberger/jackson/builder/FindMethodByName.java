package de.himberger.jackson.builder;

import java.lang.reflect.Method;

/**
 * Class to find a method specified by a certain name.
 */
public class FindMethodByName implements MethodFinder {

	private final String name;
	
	public FindMethodByName(String name) {
		super();
		if (name == null || "".equals(name.trim())) {
			throw new IllegalArgumentException(Messages.ERR_METHOD_NAME_NULL);
		}		
		this.name = name;
	}

	public String getName() {
		return name;
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
			if (method.getName().equals(name)) {
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
