package de.himberger.jackson.builder;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.ObjectMapper;

public class BuilderModule extends Module {

	public static final Version CURRENT_VERSION = new Version(1,0,0, "");
	
	private final Class builderMethodAnnotation;
	
	private final ObjectMapper mapper;
	
	public BuilderModule(ObjectMapper mapper,Class builderMethodAnnotation) {
		super();
		this.builderMethodAnnotation = builderMethodAnnotation;
		this.mapper = mapper;
	}

	@Override
	public String getModuleName() {
		return BuilderModule.class.getSimpleName();
	}

	@Override
	public Version version() {
		return CURRENT_VERSION;
	}

	
	
	@Override
	public void setupModule(SetupContext context) {
		context.appendAnnotationIntrospector(new BuilderAnnotationIntrospector(mapper,builderMethodAnnotation));
		
	}

}
