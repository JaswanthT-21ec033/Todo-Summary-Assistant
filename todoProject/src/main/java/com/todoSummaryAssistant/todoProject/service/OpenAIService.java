package com.todoSummaryAssistant.todoProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenAIService {
	
	  @Value("${openai.api.key}")
	    private String openAiApiKey;

	    public String summarizeTodos(List<String> todos) {
	        String prompt = "Summarize the following todos:\n" + String.join("\n", todos);

	        WebClient client = WebClient.builder()
	                .baseUrl("https://api.openai.com/v1")
	                .defaultHeader("Authorization", "Bearer " + openAiApiKey)
	                .build();

	        String body = """
	            {
	              "model": "gpt-3.5-turbo",
	              "messages": [
	                {"role": "user", "content": "%s"}
	              ]
	            }
	            """.formatted(prompt);

	        return client.post()
	                .uri("/chat/completions")
	                .header("Content-Type", "application/json")
	                .bodyValue(body)
	                .retrieve()
	                .bodyToMono(String.class)
	                .map(response -> response.split("\"content\":\"")[1].split("\"")[0])
	                .block();
	    }

		
	}
	


