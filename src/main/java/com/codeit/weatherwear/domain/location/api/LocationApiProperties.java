package com.codeit.weatherwear.domain.location.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "location")
public record LocationApiProperties(String apiUrl, String apiKey) {

}
