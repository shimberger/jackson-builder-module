package de.himberger.jackson.builder;

import static org.junit.Assert.assertNotNull;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import de.himberger.jackson.builder.model.CreatesBuilder;
import de.himberger.jackson.builder.model.CreatesInstance;

public class BuilderModuleConfigurationTestCase {
	
	@Test
	public void validConstructionWorks() {
		BuilderModuleConfiguration conf = BuilderModuleConfiguration.getBuilder()
			.setObjectMapper(new ObjectMapper())
			.setCreateBuilderMethodFinder(new FindMethodByAnnotation(CreatesBuilder.class))
			.setCreateInstanceMethodFinder(new FindMethodByAnnotation(CreatesInstance.class))
			.build();
		assertNotNull(conf);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullCreateBuilderMethodFinderThrowsException() {
		BuilderModuleConfiguration conf = BuilderModuleConfiguration.getBuilder()
				.setObjectMapper(new ObjectMapper())
				.setCreateBuilderMethodFinder(null)
				.setCreateInstanceMethodFinder(new FindMethodByAnnotation(CreatesInstance.class))
				.build();		
	}	

	@Test(expected=IllegalArgumentException.class)
	public void nullCreateInstanceMethodFinderThrowsException() {
		BuilderModuleConfiguration conf = BuilderModuleConfiguration.getBuilder()
				.setObjectMapper(new ObjectMapper())
				.setCreateBuilderMethodFinder(new FindMethodByAnnotation(CreatesBuilder.class))
				.setCreateInstanceMethodFinder(null)
				.build();		
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void nullObjectMapperThrowsException() {
		BuilderModuleConfiguration conf = BuilderModuleConfiguration.getBuilder()
				.setObjectMapper(null)
				.setCreateBuilderMethodFinder(new FindMethodByAnnotation(CreatesBuilder.class))
				.setCreateInstanceMethodFinder(new FindMethodByAnnotation(CreatesInstance.class))
				.build();		
	}	
	
}
