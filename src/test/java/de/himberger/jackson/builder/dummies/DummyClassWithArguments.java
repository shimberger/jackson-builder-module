package de.himberger.jackson.builder.dummies;

import de.himberger.jackson.builder.model.CreatesBuilder;
import de.himberger.jackson.builder.model.CreatesInstance;

public class DummyClassWithArguments {

	@CreatesBuilder
	public static Object getBuilder(String name) {
		return null;
	}
	
	@CreatesInstance
	public Object create(String name) {
		return null;
	}	
	
}
