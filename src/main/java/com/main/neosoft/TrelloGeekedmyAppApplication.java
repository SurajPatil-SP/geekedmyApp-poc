package com.main.neosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EntityScan
@EnableJpaRepositories
//@EnableMongoAuditing
@SpringBootApplication
public class TrelloGeekedmyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloGeekedmyAppApplication.class, args);
	}

}
