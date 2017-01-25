package com.github.leandrohsilveira.specfy;

public class Spec {

	protected static abstract class Composer {

		protected abstract String compose();

	}

	public static class SpecSchema extends Composer {

		public SpecSchema(String schema) {
			this.schema = schema;
		}

		private String schema;

		public SpecHost host(String host) {
			return new SpecHost(this, host);
		}

		@Override
		protected String compose() {
			return schema;
		}

		public static class SpecHost extends Composer {

			private SpecSchema resourceSchema;
			private String host;
			private Integer port;

			public SpecHost(SpecSchema resourceSchema, String host) {
				this.resourceSchema = resourceSchema;
				this.host = host;
			}

			public SpecHost port(Integer port) {
				if (port != null) this.port = port;
				return this;
			}

			@Deprecated
			public RESTfulClientSpec api(String resourcesRoot) {
				return new RESTfulClientSpec(SpecHost.this.compose(), resourcesRoot);
			}

			@Override
			protected String compose() {
				String result = resourceSchema.compose().concat("://").concat(host);
				if (port != null) result = result.concat(":").concat(port.toString());
				return result;
			}

		}

	}

	public static SpecSchema https() {
		return new SpecSchema("https");
	}

	public static SpecSchema http() {
		return new SpecSchema("http");
	}

}
