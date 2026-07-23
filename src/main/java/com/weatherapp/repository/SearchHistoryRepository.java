package com.weatherapp.repository;

import com.weatherapp.entity.SearchHistory;
import com.weatherapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findTop10ByUserOrderBySearchTimeDesc(User user);
}
