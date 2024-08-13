package com.sparta.scheduler.repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class EventRepository {
    private final JdbcTemplate jdbcTemplate;
    public EventRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
