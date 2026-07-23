package com.weatherapp.controller;

import com.weatherapp.dto.FavoriteCityDto;
import com.weatherapp.service.FavoriteCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favorite Cities Controller", description = "Protected endpoints to manage user saved favorite locations")
@SecurityRequirement(name = "bearerAuth")
public class FavoriteCityController {

    private final FavoriteCityService favoriteCityService;

    public FavoriteCityController(FavoriteCityService favoriteCityService) {
        this.favoriteCityService = favoriteCityService;
    }

    @GetMapping
    @Operation(summary = "Get list of saved favorite cities for logged-in user")
    public ResponseEntity<List<FavoriteCityDto>> getFavorites() {
        return ResponseEntity.ok(favoriteCityService.getFavoriteCitiesForCurrentUser());
    }

    @PostMapping
    @Operation(summary = "Add a city to favorites")
    public ResponseEntity<FavoriteCityDto> addFavorite(@Valid @RequestBody FavoriteCityDto dto) {
        FavoriteCityDto saved = favoriteCityService.addFavoriteCity(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a favorite city by ID")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        favoriteCityService.deleteFavoriteCity(id);
        return ResponseEntity.noContent().build();
    }
}
