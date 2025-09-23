package com.codeit.weatherwear.domain.weather.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather")
public record WeatherApiProperties(String apiUrl, String apiServiceKey) {

}
