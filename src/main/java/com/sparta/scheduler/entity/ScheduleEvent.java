package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.EventRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleEvent {
    private Long eventId;
    private String eventName;
    private Long managerId;
    private String password;
    private Date createDate;
    private Date upToDate;

    public ScheduleEvent(EventRequestDto requestDto){
        this.eventName = requestDto.getEventName();
        this.managerId = requestDto.getManagerId();
        this.password = requestDto.getPassword();
        if (requestDto.getCreateDate() != null){
            this.upToDate = requestDto.getUpToDate();
        } else {
            this.createDate = new Date();
            this.upToDate = new Date();
        }

    }

}
