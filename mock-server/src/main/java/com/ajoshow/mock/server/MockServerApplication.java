package com.ajoshow.mock.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ajohsow"})
@EntityScan(basePackages = {"com.ajoshow"})
@SpringBootApplication(scanBasePackages = {"com.ajoshow"})
public class MockServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockServerApplication.class, args);
	}

}
