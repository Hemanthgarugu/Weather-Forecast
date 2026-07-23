package com.weatherapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_cities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "cityName"})
})
public class FavoriteCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String cityName;

    private String country;
    private Double latitude;
    private Double longitude;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public FavoriteCity() {}

    public FavoriteCity(Long id, User user, String cityName, String country, Double latitude, Double longitude, LocalDateTime addedAt) {
        this.id = id;
        this.user = user;
        this.cityName = cityName;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addedAt = addedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

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
        private User user;
        private String cityName;
        private String country;
        private Double latitude;
        private Double longitude;
        private LocalDateTime addedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder cityName(String cityName) { this.cityName = cityName; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder latitude(Double latitude) { this.latitude = latitude; return this; }
        public Builder longitude(Double longitude) { this.longitude = longitude; return this; }
        public Builder addedAt(LocalDateTime addedAt) { this.addedAt = addedAt; return this; }

        public FavoriteCity build() {
            return new FavoriteCity(id, user, cityName, country, latitude, longitude, addedAt);
        }
    }
}
