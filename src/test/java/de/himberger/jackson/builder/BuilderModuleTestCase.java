package de.himberger.jackson.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import de.himberger.jackson.builder.model.BuilderMethod;
import de.himberger.jackson.builder.model.Configuration;
import de.himberger.jackson.builder.model.PluginConfiguration;

public class BuilderModuleTestCase {

	private ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new BuilderModule(mapper,BuilderMethod.class));
		return mapper;
	}
	
	@Test
	public void simpleUsage() throws Exception {
		Configuration conf = getMapper().readValue(TestData.get("simple"),Configuration.class);
		assertNotNull(conf);
		assertNotNull(conf.getServer());
		assertEquals("Server Name",conf.getServer().getName());
		assertEquals(10,conf.getServer().getPort());
		assertEquals("1.0.0",conf.getServer().getVersion());
	}
	
	@Test
	public void complexUsage() throws Exception {
		Configuration conf = getMapper().readValue(TestData.get("complex"),Configuration.class);
		assertNotNull(conf);
		assertNotNull(conf.getServer());
		assertEquals("Server Name",conf.getServer().getName());
		assertEquals(10,conf.getServer().getPort());
		assertEquals("1.0.0",conf.getServer().getVersion());
		assertNotNull(conf.getPlugins());
		assertEquals(2,conf.getPlugins().size());
		
		PluginConfiguration p1 = conf.getPlugins().get(0);
		assertNotNull(p1);
		assertEquals("Plugin 1",p1.getName());
		assertEquals(2,p1.getParams().size());
		assertEquals("value-1",p1.getParams().get("arg-1"));
		
		PluginConfiguration p2 = conf.getPlugins().get(1);
		assertEquals("Plugin 2",p2.getName());	
		assertEquals(0,p2.getParams().size());
	}	
	
}
