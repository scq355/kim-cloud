package com.kim.providerreactiveweb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Provider Reactive Web",
		version = "1.0",
		description = "Provider Reactive Web"
))
@SpringBootApplication
public class ProviderReactiveWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderReactiveWebApplication.class, args);
	}

}
