package de.himberger.jackson.builder;

import java.io.IOException;
import java.lang.reflect.Method;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class BuilderDeserializer extends JsonDeserializer {

	private final BuilderModuleConfiguration configuration;
	private final Method builderMethod;
	
	public BuilderDeserializer(BuilderModuleConfiguration configuration, Method builderMethod) {
		super();
		this.configuration = configuration;
		this.builderMethod = builderMethod;
	}

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			Object o = builderMethod.invoke(builderMethod.getDeclaringClass(),new Object[0]);
			//TODO: This is stupid since the builder has to have a public no-args constructor
			Object builder = configuration.getObjectMapper().readValue(jp,o.getClass());
			Method createInstanceMethod = configuration.getCreateInstanceMethodFinder().findIn(builder.getClass());
			if (createInstanceMethod != null) {
				return createInstanceMethod.invoke(builder,new Object[0]);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
