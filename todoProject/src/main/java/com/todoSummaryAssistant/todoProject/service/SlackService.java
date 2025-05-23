package com.todoSummaryAssistant.todoProject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service 
public class SlackService {
	
	 @Value("${slack.webhook.url}")
	    private String slackWebhookUrl;

	    public void postToSlack(String message) {
	        WebClient.create()
	                .post()
	                .uri(slackWebhookUrl)
	                .bodyValue("{\"text\":\"" + message + "\"}")
	                .retrieve()
	                .toBodilessEntity()
	                .block();
	    }

}
