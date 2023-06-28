package com.microservice.api;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableZuulProxy
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.microservice.api" })// gabisa swagger, tpi bisa zuul
//@ComponentScan(basePackages = { "HAHAHAHAHAHAHA" })// gabisa swagger, tpi bisa zuul
//@ComponentScan(basePackages = { "com.microservice" }) // bisa swagger, tpi gbisa zuul
//@ComponentScan()// bisa swagger, gbisa zuul
//@ComponentScan// sama
//@ComponentScan(basePackages = { "com.microservice" })
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties
public class ApiServiceApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(ApiServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		builder.setReadTimeout(Duration.ofMinutes(1));
		builder.setConnectTimeout(Duration.ofMinutes(1));
		
		return builder.build();
	}
	
	@Bean
	public Docket api() {
		Contact contact = new Contact("name", "http://tritronik.com", "risna.hendayana@tritronik.com");
//		ApiInfo apiInfo = new ApiInfo("Smart Solution Platform Service",
//				"Composite Service for Smart Solution Platform", "1.0", "http://tritronik.com", contact,
//				"license", "http://tritronik.com/", new ArrayList<>());

		return new Docket(DocumentationType.SWAGGER_2);
	}
	
	public void run(String... args) throws Exception {
		logger.info("Running Smart Solution Gateway Service ... ");
	}
	
	
	@GetMapping("/")
	public String welcome() {
		return "welcome at Smart Solution Gateway Service";
	}
}
