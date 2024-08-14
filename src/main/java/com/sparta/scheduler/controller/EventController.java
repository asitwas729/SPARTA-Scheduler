package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.EventRequestDto;
import com.sparta.scheduler.dto.EventResponseDto;
import com.sparta.scheduler.entity.ScheduleEvent;
import com.sparta.scheduler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public EventResponseDto createEvent(@RequestBody EventRequestDto requestDto){
        return eventService.createEvent(requestDto);
    }

    @GetMapping("/event/{id}")
    public ScheduleEvent findById(@PathVariable("id") Long eventId){
        return eventService.findById(eventId);
    }

    @GetMapping("/event")
    public List<EventResponseDto> getEvents(){
        return eventService.getEvents();
    }

//    @PutMapping("/event/{id}/{password}")
//    public Long updateEvent(@PathVariable("id") Long eventId, @PathVariable("password") String password, @RequestBody EventRequestDto requestDto){
//        // 수정할때, password까지 맞아야 수정창으로 넘어가야될것같아서 password도 따로 받음
//        // 근데? password를 url에 받으면 유출되지않을까?(다 만들고 생각해보기!)
//        return eventService.updateEvent(eventId, password, requestDto);
//    }
    @PutMapping("/event/{id}")
    public ScheduleEvent updateEvent(@PathVariable("id") Long eventId, @RequestBody EventRequestDto requestDto){
        // 그래두 body-raw에 json형식으로 비밀번호를 포함하는 방법이 더 보안에 좋을것같아서 변경
        return eventService.updateEvent(eventId, requestDto);
    }

}
