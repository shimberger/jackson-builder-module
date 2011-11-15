package de.himberger.jackson.builder;

import org.codehaus.jackson.map.ObjectMapper;

public class BuilderModuleConfiguration {

	private final MethodFinder createBuilderMethodFinder;
	
	private final MethodFinder createInstanceMethodFinder;
	
	private final ObjectMapper objectMapper;	
	
	public static class Builder {
		
		private MethodFinder createBuilderMethodFinder;
		
		private MethodFinder createInstanceMethodFinder;
		
		private ObjectMapper objectMapper;

		private Builder() {
			
		}
		
		public MethodFinder getCreateBuilderMethodFinder() {
			return createBuilderMethodFinder;
		}

		public Builder setCreateBuilderMethodFinder(MethodFinder createBuilderMethodFinder) {
			this.createBuilderMethodFinder = createBuilderMethodFinder;
			return this;
		}

		public MethodFinder getCreateInstanceMethodFinder() {
			return createInstanceMethodFinder;
		}

		public Builder setCreateInstanceMethodFinder(
				MethodFinder createInstanceMethodFinder) {
			this.createInstanceMethodFinder = createInstanceMethodFinder;
			return this;
		}

		public ObjectMapper getObjectMapper() {
			return objectMapper;
		}

		public Builder setObjectMapper(ObjectMapper objectMapper) {
			this.objectMapper = objectMapper;
			return this;
		}
		
		public BuilderModuleConfiguration build() {
			return new BuilderModuleConfiguration(this);
		}
		
	}
	
	private BuilderModuleConfiguration(Builder builder) {
		createInstanceMethodFinder = builder.getCreateInstanceMethodFinder();
		createBuilderMethodFinder = builder.getCreateBuilderMethodFinder();
		objectMapper = builder.getObjectMapper();
		throwExceptionIfInvalid();
	}
	
	private void throwExceptionIfInvalid() {
		if (createInstanceMethodFinder == null) {
			throw new IllegalArgumentException(Messages.ERR_CREATE_INSTANCE_FINDER_NULL);
		}
		if (createBuilderMethodFinder == null) {
			throw new IllegalArgumentException(Messages.ERR_CREATE_BUILDER_FINDER_NULL);
		}		
		if (objectMapper == null) {
			throw new IllegalArgumentException(Messages.ERR_OBJECT_MAPPER_NULL);
		}		
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}

	public MethodFinder getCreateBuilderMethodFinder() {
		return createBuilderMethodFinder;
	}

	public MethodFinder getCreateInstanceMethodFinder() {
		return createInstanceMethodFinder;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
}
