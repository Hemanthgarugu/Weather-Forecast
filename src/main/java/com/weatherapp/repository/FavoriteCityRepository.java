package com.weatherapp.repository;

import com.weatherapp.entity.FavoriteCity;
import com.weatherapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {
    List<FavoriteCity> findByUserOrderByAddedAtDesc(User user);
    Optional<FavoriteCity> findByUserAndCityNameIgnoreCase(User user, String cityName);
    Boolean existsByUserAndCityNameIgnoreCase(User user, String cityName);
}
