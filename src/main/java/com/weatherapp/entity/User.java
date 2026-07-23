package com.weatherapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteCity> favoriteCities = new ArrayList<>();

    public User() {}

    public User(Long id, String username, String email, String password, Role role, LocalDateTime createdAt, List<FavoriteCity> favoriteCities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role != null ? role : Role.ROLE_USER;
        this.createdAt = createdAt;
        this.favoriteCities = favoriteCities != null ? favoriteCities : new ArrayList<>();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<FavoriteCity> getFavoriteCities() { return favoriteCities; }
    public void setFavoriteCities(List<FavoriteCity> favoriteCities) { this.favoriteCities = favoriteCities; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private Role role = Role.ROLE_USER;
        private LocalDateTime createdAt;
        private List<FavoriteCity> favoriteCities = new ArrayList<>();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder role(Role role) { this.role = role; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder favoriteCities(List<FavoriteCity> favoriteCities) { this.favoriteCities = favoriteCities; return this; }

        public User build() {
            return new User(id, username, email, password, role, createdAt, favoriteCities);
        }
    }
}
