package com.weatherapp.service;

import com.weatherapp.dto.FavoriteCityDto;
import com.weatherapp.entity.FavoriteCity;
import com.weatherapp.entity.User;
import com.weatherapp.exception.ApiException;
import com.weatherapp.repository.FavoriteCityRepository;
import com.weatherapp.repository.UserRepository;
import com.weatherapp.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteCityService {

    private final FavoriteCityRepository favoriteCityRepository;
    private final UserRepository userRepository;

    public FavoriteCityService(FavoriteCityRepository favoriteCityRepository, UserRepository userRepository) {
        this.favoriteCityRepository = favoriteCityRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<FavoriteCityDto> getFavoriteCitiesForCurrentUser() {
        User user = getCurrentAuthenticatedUser();
        return favoriteCityRepository.findByUserOrderByAddedAtDesc(user)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FavoriteCityDto addFavoriteCity(FavoriteCityDto dto) {
        User user = getCurrentAuthenticatedUser();

        if (favoriteCityRepository.existsByUserAndCityNameIgnoreCase(user, dto.getCityName())) {
            throw new ApiException("City is already in your favorites!", HttpStatus.BAD_REQUEST);
        }

        FavoriteCity city = FavoriteCity.builder()
                .user(user)
                .cityName(dto.getCityName())
                .country(dto.getCountry())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        FavoriteCity saved = favoriteCityRepository.save(city);
        return mapToDto(saved);
    }

    @Transactional
    public void deleteFavoriteCity(Long id) {
        User user = getCurrentAuthenticatedUser();
        FavoriteCity city = favoriteCityRepository.findById(id)
                .orElseThrow(() -> new ApiException("Favorite city not found with id: " + id, HttpStatus.NOT_FOUND));

        if (!city.getUser().getId().equals(user.getId())) {
            throw new ApiException("Unauthorized to delete this favorite city", HttpStatus.FORBIDDEN);
        }

        favoriteCityRepository.delete(city);
    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            throw new ApiException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    }

    private FavoriteCityDto mapToDto(FavoriteCity city) {
        return FavoriteCityDto.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .country(city.getCountry())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .addedAt(city.getAddedAt())
                .build();
    }
}
