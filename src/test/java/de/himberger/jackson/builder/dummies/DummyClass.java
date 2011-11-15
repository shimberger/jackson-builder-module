package de.himberger.jackson.builder.dummies;

import de.himberger.jackson.builder.model.CreatesBuilder;
import de.himberger.jackson.builder.model.CreatesInstance;

public class DummyClass {

	@CreatesBuilder
	public static Object getBuilder() {
		return null;
	}
	
	@CreatesInstance
	public Object build() {
		return null;
	}	
	
}
