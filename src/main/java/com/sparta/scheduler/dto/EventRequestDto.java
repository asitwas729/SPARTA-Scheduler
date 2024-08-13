package com.sparta.scheduler.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class EventRequestDto {
    private String eventName;
    private String managerName;
    private String password;
    private Date upToDate;
    private Date createDate;
}
