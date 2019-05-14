package com.ncherkas.hazelcast.cloud.demo;

import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.spi.impl.discovery.HazelcastCloudDiscovery;
import com.hazelcast.client.spi.properties.ClientProperty;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.ncherkas.hazelcast.cloud.demo.function.KeepAliveService;
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
		config.setProperty(ClientProperty.HAZELCAST_CLOUD_DISCOVERY_TOKEN.getName(), hazelcastCloudProps.getDiscoveryToken());
		config.setProperty(HazelcastCloudDiscovery.CLOUD_URL_BASE_PROPERTY.getName(), hazelcastCloudProps.getUrlBase());
		config.setProperty("hazelcast.client.heartbeat.timeout", "300000");
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

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule module = new JavaTimeModule();
		objectMapper.registerModule(module);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper;
	}

	@Bean
	public KeepAliveService keepAliveService() {
		return LambdaInvokerFactory.builder()
				.lambdaClient(AWSLambdaClientBuilder.defaultClient())
				.build(KeepAliveService.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FunctionApp.class, args);
	}
}

