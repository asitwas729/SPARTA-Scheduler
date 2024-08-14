package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.ScheduleEvent;
import lombok.Getter;

import java.util.Date;

@Getter
public class EventResponseDto {
    private Long eventId;
    private String eventName;
    private String managerName;
    private Date createDate;
    private Date upToDate;

    public EventResponseDto(ScheduleEvent event) {
        this.eventId = event.getEventId();
        this.eventName = event.getEventName();
        this.managerName = event.getManagerName();
        this.createDate = event.getCreateDate();
        this.upToDate = event.getUpToDate();
    }

    // DB에서 가져온 값
    public EventResponseDto(Long eventId, String eventName, String managerName, java.sql.Date createDate, java.sql.Date upToDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.managerName = managerName;
        this.createDate = createDate;
        this.upToDate = upToDate;
    }
}
