package com.mongoExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MongoDbExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbExamApplication.class, args);
	}

}
