package com.ncherkas.hazelcast.cloud.demo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.spi.impl.discovery.HazelcastCloudDiscovery;
import com.hazelcast.client.spi.properties.ClientProperty;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.ncherkas.hazelcast.cloud.demo.model.Airport;
import com.ncherkas.hazelcast.cloud.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FunctionApp {

	@Bean
	public HazelcastInstance hazelcastClient(Properties props) {
		System.out.println("Using: " + props);
		Properties.HazelcastCloud hazelcastCloudProps = props.getHazelcastCloud();
		ClientConfig config = new ClientConfig();
		config.setGroupConfig(new GroupConfig(hazelcastCloudProps.getClusterName(), hazelcastCloudProps.getClusterPassword()));
		config.setProperty("hazelcast.client.statistics.enabled", "true");
		config.setProperty("hazelcast.client.heartbeat.timeout", "300000");
		config.setProperty(ClientProperty.HAZELCAST_CLOUD_DISCOVERY_TOKEN.getName(), hazelcastCloudProps.getDiscoveryToken());
		config.setProperty(HazelcastCloudDiscovery.CLOUD_URL_BASE_PROPERTY.getName(), hazelcastCloudProps.getUrlBase());
		return HazelcastClient.newHazelcastClient(config);
	}

	@Bean
	public IMap<Integer, User> usersMap(HazelcastInstance hazelcastClient) {
		return hazelcastClient.getMap("users");
	}

	@Bean
	public IMap<String, Airport> airportsMap(HazelcastInstance hazelcastClient) {
		return hazelcastClient.getMap("airports");
	}

	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.defaultClient();
	}

	public static void main(String[] args) {
		SpringApplication.run(FunctionApp.class, args);
	}
}

