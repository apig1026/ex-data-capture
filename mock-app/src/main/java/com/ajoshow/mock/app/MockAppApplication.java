package com.ajoshow.mock.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ajohsow"})
@EntityScan(basePackages = {"com.ajoshow"})
@SpringBootApplication(scanBasePackages = {"com.ajoshow"})
public class MockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		builder.setConnectTimeout(1000 * 60);
		builder.setReadTimeout(1000 * 15);
		return builder.build();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.configure(SerializationFeature.INDENT_OUTPUT, true);
	}
}
