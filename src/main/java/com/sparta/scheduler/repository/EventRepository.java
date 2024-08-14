package com.sparta.scheduler.repository;

import com.sparta.scheduler.dto.EventRequestDto;
import com.sparta.scheduler.dto.EventResponseDto;
import com.sparta.scheduler.entity.ScheduleEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;
    public EventRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    Date today = new Date(System.currentTimeMillis());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    // 일정 저장
    public ScheduleEvent eventSave(ScheduleEvent scheduleEvent){
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO scheduler (eventName, managerName, password, upToDate, createDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, scheduleEvent.getEventName());
            preparedStatement.setString(2, scheduleEvent.getManagerName());
            preparedStatement.setString(3, scheduleEvent.getPassword());
            preparedStatement.setString(4, dateFormat.format(today));
            preparedStatement.setString(5, dateFormat.format(today));
            return preparedStatement;
        },
            keyHolder);

        Long id = keyHolder.getKey().longValue();
        scheduleEvent.setEventId(id);

        return scheduleEvent;
    }

    // 일정 조회
    public ScheduleEvent findById(Long eventId){
        // DB 조회
        String sql = "SELECT * FROM scheduler WHERE eventId = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                ScheduleEvent event = new ScheduleEvent();
                event.setEventId(resultSet.getLong("eventId"));
                event.setEventName(resultSet.getString("eventName"));
                event.setManagerName(resultSet.getString("managerName"));
                event.setCreateDate(resultSet.getDate("createDate"));
                event.setUpToDate(resultSet.getDate("upToDate"));
                return event;
            } else {
                return null;
            }
        }, eventId);
    }

    
    // 일정 목록 조회
    public List<EventResponseDto> findAll(){
        // DB 조회
        String sql = "SELECT eventId, eventName, managerName, createDate, upToDate FROM scheduler ORDER BY upToDate DESC";

        return jdbcTemplate.query(sql, new RowMapper<EventResponseDto>() {
            @Override
            public EventResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // sql(scheduler data) -> eventResponseDto
                Long eventId = rs.getLong("eventId");
                String eventName = rs.getString("eventName");
                String managerName = rs.getString("managerName");
                Date createDate = rs.getDate("createDate");
                Date upToDate = rs.getDate("upToDate");
                //upToDate = Date.valueOf(simpleDateFormat.format(upToDate));   // 굳이 안해도 Date형식(yyyy-MM-dd)

                return new EventResponseDto(eventId, eventName, managerName, createDate, upToDate);
            }
        });
    }
    
    // 일정 수정
    public void updateEvent(Long eventId, EventRequestDto requestDto){
        // DB 조회
        String sql = "UPDATE scheduler SET eventName = ?, managerName = ?, upToDate = ? WHERE eventId = ? AND password = ?";
        jdbcTemplate.update(sql, requestDto.getEventName(), requestDto.getManagerName(), dateFormat.format(today), eventId, requestDto.getPassword());
    }
//    public void updateEvent(Long eventId, String password, EventRequestDto requestDto){
//        // DB 조회
//        String sql = "UPDATE scheduler SET eventName = ?, managerName = ?, upToDate = ? WHERE eventId = ? AND password = ?";
//        jdbcTemplate.update(sql, requestDto.getEventName(), requestDto.getManagerName(), dateFormat.format(today), eventId, password);
//    }

    // 비밀번호 맞는지
    public ScheduleEvent findByPassword(Long eventId, String password){
        // DB 조회
        String sql = "SELECT * FROM scheduler WHERE eventId = ? and password = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                ScheduleEvent event = new ScheduleEvent();
                event.setEventName(resultSet.getString("eventName"));
                event.setManagerName(resultSet.getString("managerName"));
                event.setCreateDate(resultSet.getDate("createDate"));
                event.setUpToDate(resultSet.getDate("upToDate"));
                return event;
            } else {
                return null;
            }
        }, eventId, password);
    }
    
    // 일정 삭제
}
