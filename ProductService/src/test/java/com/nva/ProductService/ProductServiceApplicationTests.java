package com.nva.ProductService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nva.ProductService.dto.request.ProductDTORequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static GenericContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.8").withExposedPorts(27017);

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MockMvc mockMvc;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry properties){
		properties.add("spring.data.mongodb.uri", () -> {
			String address = mongoDBContainer.getContainerIpAddress();
			Integer port = mongoDBContainer.getMappedPort(27017);
			return "mongodb://" + address + ":" + port + "product-service";
		});
	}

	@Test
	void contextLoads() throws Exception {
		ProductDTORequest request = getProductRequest();
		String productRequest = objectMapper.writeValueAsString(request);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequest))
				.andExpect(status().isCreated());
	}

	private ProductDTORequest getProductRequest() {
		return ProductDTORequest.builder()
				.name("Test04")
				.description("Doiden")
				.price(BigDecimal.valueOf(12311))
				.build();
	}

}
