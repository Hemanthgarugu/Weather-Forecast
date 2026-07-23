package com.weatherapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String searchQuery;

    @Column(name = "search_time")
    private LocalDateTime searchTime;

    public SearchHistory() {}

    public SearchHistory(Long id, User user, String searchQuery, LocalDateTime searchTime) {
        this.id = id;
        this.user = user;
        this.searchQuery = searchQuery;
        this.searchTime = searchTime;
    }

    @PrePersist
    protected void onCreate() {
        this.searchTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getSearchQuery() { return searchQuery; }
    public void setSearchQuery(String searchQuery) { this.searchQuery = searchQuery; }

    public LocalDateTime getSearchTime() { return searchTime; }
    public void setSearchTime(LocalDateTime searchTime) { this.searchTime = searchTime; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private String searchQuery;
        private LocalDateTime searchTime;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder searchQuery(String searchQuery) { this.searchQuery = searchQuery; return this; }
        public Builder searchTime(LocalDateTime searchTime) { this.searchTime = searchTime; return this; }

        public SearchHistory build() {
            return new SearchHistory(id, user, searchQuery, searchTime);
        }
    }
}
