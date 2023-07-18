package com.microservice.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public List<SwaggerResource> get() {
    	logger.info("Running Smart Solution Gateway Service ... hahaha ");
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("hardware-service", "/api/hardware-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("software-service", "/api/software-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("producer", "/api/producer/v2/api-docs", "2.0"));
        //resources.add(swaggerResource("producer", "http://localhost:8092/api/producer/v2/api-docs", "2.0"));
        return resources;
    }
    public void run(String... args) throws Exception {
		logger.info("woi lah ... ");
	}
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
 
}
