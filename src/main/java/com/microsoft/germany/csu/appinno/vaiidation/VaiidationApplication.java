package com.microsoft.germany.csu.appinno.vaiidation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;

@SpringBootApplication
public class VaiidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaiidationApplication.class, args);
	}

	@Bean
	public OpenAIClient getOpenAIClient(@Value("${azure.openai.key}") String apiKey,
			@Value("${azure.openai.endpoint}") String endpoint) {

		// assert all parameters are not null or empty
		assert apiKey != null && !apiKey.isEmpty();
		assert endpoint != null && !endpoint.isEmpty();

		OpenAIClient client = new OpenAIClientBuilder()
				.credential(new AzureKeyCredential(apiKey))
				.endpoint(endpoint)
				.buildClient();

		return client;
	}

}
