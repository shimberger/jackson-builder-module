package de.himberger.jackson.builder;

import java.io.IOException;
import java.lang.reflect.Method;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;

public class BuilderDeserializer extends JsonDeserializer {

	private final ObjectMapper mapper;
	private final Method builderMethod;
	
	public BuilderDeserializer(ObjectMapper mapper, Method builderMethod) {
		super();
		this.mapper = mapper;
		this.builderMethod = builderMethod;
	}

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			Object o = builderMethod.invoke(builderMethod.getDeclaringClass(),new Object[0]);
			//TODO: This is stupid since the builder has to have a public no-args constructor
			Object b = mapper.readValue(jp,o.getClass());
			Method[] methods = b.getClass().getMethods();
			for (Method method : methods) {
				if (method.getName().equals("create")) {
					return method.invoke(b,new Object[0]);
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
