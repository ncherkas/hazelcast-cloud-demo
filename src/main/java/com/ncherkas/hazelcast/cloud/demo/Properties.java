package com.ncherkas.hazelcast.cloud.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class Properties {

	private HazelcastCloud hazelcastCloud;

	public HazelcastCloud getHazelcastCloud() {
		return hazelcastCloud;
	}

	public void setHazelcastCloud(HazelcastCloud hazelcastCloud) {
		this.hazelcastCloud = hazelcastCloud;
	}

	public static class HazelcastCloud {

		private String clusterName;
		private String clusterPassword;
		private String discoveryToken;
		private String urlBase;

		public String getClusterName() {
			return clusterName;
		}

		public void setClusterName(String clusterName) {
			this.clusterName = clusterName;
		}

		public String getClusterPassword() {
			return clusterPassword;
		}

		public void setClusterPassword(String clusterPassword) {
			this.clusterPassword = clusterPassword;
		}

		public String getDiscoveryToken() {
			return discoveryToken;
		}

		public void setDiscoveryToken(String discoveryToken) {
			this.discoveryToken = discoveryToken;
		}

		public String getUrlBase() {
			return urlBase;
		}

		public void setUrlBase(String urlBase) {
			this.urlBase = urlBase;
		}

		@Override
		public String toString() {
			return "HazelcastCloud{" +
					"clusterName='" + clusterName + '\'' +
					", clusterPassword='" + clusterPassword + '\'' +
					", discoveryToken='" + discoveryToken + '\'' +
					", urlBase='" + urlBase + '\'' +
					'}';
		}
	}

	@Override
	public String toString() {
		return "Properties{" +
				"hazelcastCloud=" + hazelcastCloud +
				'}';
	}
}
