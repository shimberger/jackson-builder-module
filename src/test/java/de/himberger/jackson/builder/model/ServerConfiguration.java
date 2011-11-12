package de.himberger.jackson.builder.model;

public class ServerConfiguration {

	private final int port;
	private final String name;
	private final String version;

	public static class Builder {
		
		private int port;
		private String name;
		private String version;
		
		private Builder() {
			
		}

		public ServerConfiguration create() {
			return new ServerConfiguration(this);
		}
		
		public int getPort() {
			return port;
		}

		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public String getVersion() {
			return version;
		}

		public Builder setVersion(String version) {
			this.version = version;
			return this;
		}
		
	}
	
	private ServerConfiguration(Builder builder) {
		super();
		this.port = builder.getPort();
		this.name = builder.getName();
		this.version = builder.getVersion();
	}
	
	@BuilderMethod
	public static Builder getBuilder() {
		return new Builder();
	}

	
	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + port;
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerConfiguration other = (ServerConfiguration) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (port != other.port)
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
}
