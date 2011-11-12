package de.himberger.jackson.builder.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PluginConfiguration {

	private final String name;
	private final boolean enabled;
	private final Map<String,String> params;
	
	public static class Builder {
		
		private String name;
		private boolean enabled;
		private Map<String,String> params = Collections.<String,String>emptyMap();
		
		public PluginConfiguration create() {
			return new PluginConfiguration(this);
		}
		
		public String getName() {
			return name;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public Builder setEnabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}
		public Map<String, String> getParams() {
			return params;
		}
		public Builder setParams(Map<String, String> params) {
			this.params = params;
			return this;
		}		
		
	}
	
	private PluginConfiguration(Builder builder) {
		super();
		this.name = builder.getName();
		this.enabled = builder.isEnabled();
		this.params = new HashMap<String,String>(builder.getParams());
	}
	
	@BuilderMethod
	public static Builder build() {
		return new Builder();
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Map<String, String> getParams() {
		return params;
	}
	
}
