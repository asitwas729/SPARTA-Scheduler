package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.ScheduleEvent;
import lombok.Getter;

import java.util.Date;

@Getter
public class EventResponseDto {
    private Long eventId;
    private String eventName;
    private String managerName;
    private String password;
    private Date createDate;
    private Date upToDate;

    public EventResponseDto(ScheduleEvent event) {
        this.eventId = event.getEventId();
        this.eventName = event.getEventName();
        this.managerName = event.getManagerName();
        this.password = event.getPassword();
        this.createDate = event.getCreateDate();
        this.upToDate = event.getUpToDate();
    }

    public EventResponseDto(Long eventId, String eventName, String managerName, Date createDate, Date upToDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.managerName = managerName;
        this.createDate = createDate;
        this.upToDate = upToDate;
    }
}
