package com.weatherapp.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class FavoriteCityDto {

    private Long id;

    @NotBlank(message = "City name is required")
    private String cityName;

    private String country;
    private Double latitude;
    private Double longitude;
    private LocalDateTime addedAt;

    public FavoriteCityDto() {}

    public FavoriteCityDto(Long id, String cityName, String country, Double latitude, Double longitude, LocalDateTime addedAt) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addedAt = addedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String cityName;
        private String country;
        private Double latitude;
        private Double longitude;
        private LocalDateTime addedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder cityName(String cityName) { this.cityName = cityName; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder latitude(Double latitude) { this.latitude = latitude; return this; }
        public Builder longitude(Double longitude) { this.longitude = longitude; return this; }
        public Builder addedAt(LocalDateTime addedAt) { this.addedAt = addedAt; return this; }

        public FavoriteCityDto build() {
            return new FavoriteCityDto(id, cityName, country, latitude, longitude, addedAt);
        }
    }
}
