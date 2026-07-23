package com.weatherapp.controller;

import com.weatherapp.dto.WeatherDtos.WeatherResponseDto;
import com.weatherapp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@Tag(name = "Weather Controller", description = "Endpoints for current weather and multi-day forecasts")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    @Operation(summary = "Get weather metrics and 5-day forecast for a city")
    public ResponseEntity<WeatherResponseDto> getWeather(
            @Parameter(description = "City name (e.g., London, New York, Tokyo, Mumbai)", example = "London")
            @RequestParam(defaultValue = "London") String city) {
        WeatherResponseDto weather = weatherService.getWeatherByCity(city);
        return ResponseEntity.ok(weather);
    }
}
