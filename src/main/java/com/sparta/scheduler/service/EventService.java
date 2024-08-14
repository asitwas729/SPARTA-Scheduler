package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.EventRequestDto;
import com.sparta.scheduler.dto.EventResponseDto;
import com.sparta.scheduler.entity.ScheduleEvent;
import com.sparta.scheduler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public EventResponseDto createEvent(EventRequestDto requestDto){
        // requestDto -> entity
        ScheduleEvent event = new ScheduleEvent(requestDto);

        // DB 저장
        ScheduleEvent saveEvent = eventRepository.eventSave(event);

        // Entity -> ResponseDto
        return new EventResponseDto(saveEvent);
    }

    public ScheduleEvent findById(Long eventId){
        // DB 조회
        ScheduleEvent event = eventRepository.findById(eventId);
        if (event != null){
            return eventRepository.findById(eventId);
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지않습니다.");
        }
    }

    public List<EventResponseDto> getEvents(){
        return eventRepository.findAll();
    }

//    public Long updateEvent(Long eventId, String password, EventRequestDto requestDto){
//        ScheduleEvent beEvent = eventRepository.findById(eventId);
//        ScheduleEvent bePassword = eventRepository.findByPassword(eventId, password);
//        if (beEvent != null){
//            if (bePassword != null){
//                eventRepository.updateEvent(eventId, password, requestDto);
//                return eventId;
//            } else {
//                throw new IllegalArgumentException("선택한 일정의 비밀번호가 맞지않습니다.");
//            }
//        } else {
//            throw new IllegalArgumentException("선택한 일정은 존재하지않습니다.");
//        }
//
//    }
    public ScheduleEvent updateEvent(Long eventId, EventRequestDto requestDto){
        ScheduleEvent beEvent = eventRepository.findById(eventId);
        ScheduleEvent bePassword = eventRepository.findByPassword(eventId, requestDto.getPassword());
        if (beEvent != null){
            if (bePassword != null){
                eventRepository.updateEvent(eventId, requestDto);
                return eventRepository.findById(eventId);
            } else {
                throw new IllegalArgumentException("선택한 일정의 비밀번호가 맞지않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지않습니다.");
        }

    }

    public ScheduleEvent deleteEvent(Long eventId, EventRequestDto requestDto){
        ScheduleEvent beEvent = eventRepository.findById(eventId);
        ScheduleEvent bePassword = eventRepository.findByPassword(eventId, requestDto.getPassword());
        if (beEvent != null){
            if (bePassword != null){
                eventRepository.deleteEvent(eventId, requestDto);
                return eventRepository.findById(eventId);
            } else {
                throw new IllegalArgumentException("선택한 일정의 비밀번호가 맞지않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지않습니다.");
        }
    }
}
