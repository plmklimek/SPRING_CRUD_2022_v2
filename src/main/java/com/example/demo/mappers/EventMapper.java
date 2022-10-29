package com.example.demo.mappers;

import com.example.demo.dtos.EventDto;
import com.example.demo.models.Event;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static Event mapEventDtoToEvent(EventDto eventDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(eventDto, Event.class);
    }

    public static EventDto mapEventToEventDto(Event event) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(event, EventDto.class);
    }

    public static List<EventDto> mapEventsToEventsDto(List<Event> events) {
        return events.stream().map(EventMapper::mapEventToEventDto).collect(Collectors.toList());
    }

    public static List<Event> mapEventsDtoToEvents(List<EventDto> eventDtos) {
        return eventDtos.stream().map(EventMapper::mapEventDtoToEvent).collect(Collectors.toList());
    }
}
