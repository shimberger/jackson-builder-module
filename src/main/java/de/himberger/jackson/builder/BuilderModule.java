package de.himberger.jackson.builder;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.Module;

public class BuilderModule extends Module {

	public static final Version CURRENT_VERSION = new Version(1,0,0, "beta");
	
	private final BuilderModuleConfiguration configuration;
	
	public BuilderModule(BuilderModuleConfiguration configuration) {
		super();
		// Assumption: No invalid configuration can be constructed
		this.configuration = configuration;
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
		context.appendAnnotationIntrospector(new BuilderAnnotationIntrospector(configuration));
		
	}

}
