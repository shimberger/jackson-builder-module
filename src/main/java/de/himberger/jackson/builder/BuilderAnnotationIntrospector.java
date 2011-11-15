package de.himberger.jackson.builder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;

public class BuilderAnnotationIntrospector extends NopAnnotationIntrospector {

	private final Map<AnnotatedClass,BuilderDeserializer> deserializers = new HashMap<AnnotatedClass,BuilderDeserializer>();
	private final BuilderModuleConfiguration configuration;
	
	public BuilderAnnotationIntrospector(BuilderModuleConfiguration configuration) {
		super();
		this.configuration = configuration;
	}

	@Override
	public Object findDeserializer(Annotated am) {
		if (am instanceof AnnotatedClass) {
			AnnotatedClass ac = (AnnotatedClass) am;
			if (deserializers.containsKey(ac)) {
				return deserializers.get(ac);
			}
			// If there is no builder, cache the null result so we don't waste time twice
			Method createBuilderMethod = configuration.getCreateBuilderMethodFinder().findInStatic(ac.getRawType());
			if (createBuilderMethod != null) {
				BuilderDeserializer deserializer = new BuilderDeserializer(configuration,createBuilderMethod);
				deserializers.put(ac,deserializer);
				return deserializer;
			}
			deserializers.put(ac,null);
		}
		return super.findDeserializer(am);
	}
	
}
