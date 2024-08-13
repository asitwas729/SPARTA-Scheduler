package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.EventRequestDto;
import com.sparta.scheduler.dto.EventResponseDto;
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

//    @GetMapping("/event")
//    public List<EventResponseDto> getEvents(){
//        return eventService.getEvents();
//    }

}
