package de.himberger.jackson.builder;

import java.io.IOException;
import java.lang.reflect.Method;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectReader;

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
			Object builder = builderMethod.invoke(builderMethod.getDeclaringClass(),new Object[0]);
			ObjectReader reader = configuration.getObjectMapper().readerForUpdating(builder);
			reader.readValue(jp);
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
