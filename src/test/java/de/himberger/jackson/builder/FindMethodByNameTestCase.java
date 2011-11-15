package de.himberger.jackson.builder;

public class FindMethodByNameTestCase extends FindMethodAbstractTestCase {

	@Override
	public MethodFinder getNonExistingBuilderMethodFinder() {
		return new FindMethodByName("nonExistantMethodName");
	}

	@Override
	public MethodFinder getExistingBuilderMethodFinder() {
		return new FindMethodByName("getBuilder");
	}

	@Override
	public MethodFinder getNonExistingInstanceMethodFinder() {
		return new FindMethodByName("nonExistantMethodName");
	}

	@Override
	public MethodFinder getExistingInstanceMethodFinder() {
		return new FindMethodByName("build");
	}

}
