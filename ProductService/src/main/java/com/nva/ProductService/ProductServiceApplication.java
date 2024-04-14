package com.nva.ProductService;

import com.nva.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.nva.ProductService.repository"})
public class ProductServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
