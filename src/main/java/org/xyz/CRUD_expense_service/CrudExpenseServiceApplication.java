package org.xyz.CRUD_expense_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.xyz.CRUD_expense_service.client")
public class CrudExpenseServiceApplication {

	public static void main(String[] args) {
        Dotenv.configure()
                .systemProperties()
                .load();
		SpringApplication.run(CrudExpenseServiceApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public feign.codec.ErrorDecoder errorDecoder() {
		return new org.xyz.CRUD_expense_service.config.CustomErrorDecoder();
	}

}
