package de.himberger.jackson.builder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;

public class BuilderAnnotationIntrospector extends NopAnnotationIntrospector {

	private final Class builderMethodAnnotation;
	private final Map<AnnotatedClass,Object> deserializers = new HashMap<AnnotatedClass,Object>();
	private final ObjectMapper mapper;
	
	public BuilderAnnotationIntrospector(ObjectMapper mapper,Class builderMethodAnnotation) {
		super();
		this.builderMethodAnnotation = builderMethodAnnotation;
		this.mapper = mapper;
	}

	@Override
	public Object findDeserializer(Annotated am) {
		if (am instanceof AnnotatedClass) {
			AnnotatedClass ac = (AnnotatedClass) am;
			if (deserializers.containsKey(ac)) {
				return deserializers.get(ac);
			}
			Class clazz = ac.getRawType();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getAnnotation(builderMethodAnnotation) != null) {
					JsonDeserializer<?> deserializer = new BuilderDeserializer(mapper, method);
					deserializers.put(ac,deserializer);
					return deserializer;
				}
			}
			// If there is no builder, cache the null
			deserializers.put(ac,null);
		}
		return super.findDeserializer(am);
	}

	
	
}
