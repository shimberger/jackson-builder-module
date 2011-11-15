package de.himberger.jackson.builder;

import de.himberger.jackson.builder.dummies.DummyAnnotation;
import de.himberger.jackson.builder.model.CreatesBuilder;
import de.himberger.jackson.builder.model.CreatesInstance;

public class FindMethodByAnnotationTestCase extends FindMethodAbstractTestCase {

	@Override
	public MethodFinder getNonExistingBuilderMethodFinder() {
		return new FindMethodByAnnotation(DummyAnnotation.class);
	}

	@Override
	public MethodFinder getExistingBuilderMethodFinder() {
		return new FindMethodByAnnotation(CreatesBuilder.class);
	}

	@Override
	public MethodFinder getNonExistingInstanceMethodFinder() {
		return new FindMethodByAnnotation(DummyAnnotation.class);
	}

	@Override
	public MethodFinder getExistingInstanceMethodFinder() {
		return new FindMethodByAnnotation(CreatesInstance.class);
	}
	
}
