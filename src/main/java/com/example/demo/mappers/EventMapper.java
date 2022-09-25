package com.example.demo.mappers;

import com.example.demo.dtos.EventDto;
import com.example.demo.models.Event;

import java.util.Set;
import java.util.stream.Collectors;

public class EventMapper {
    public static Event mapEventDtoToEvent(EventDto eventDto) {
        if(eventDto == null){
            return null;
        }

        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .invitations(InvitationMapper.mapInvitationsDtoToInvitations(eventDto.getInvitations()))
                .owner(UserMapper.mapUserDtoToUserWithoutRelations(eventDto.getOwner()))
                .build();
    }

    public static EventDto mapEventToEventDto(Event event) {
        if(event == null){
            return null;
        }

        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .invitations(InvitationMapper.mapInvitationsToInvitationsDto(event.getInvitations()))
                .owner(UserMapper.mapUserToUserDtoWithoutRelations(event.getOwner()))
                .build();
    }

    public static Event mapEventDtoToEventWithoutRelations(EventDto eventDto) {
        if(eventDto == null){
            return null;
        }

        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .owner(UserMapper.mapUserDtoToUser(eventDto.getOwner()))
                .build();
    }

    public static EventDto mapEventToEventDtoWithoutRelations(Event event) {
        if(event == null){
            return null;
        }

        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .owner(UserMapper.mapUserToUserDto(event.getOwner()))
                .build();
    }

    public static Set<EventDto> mapEventsToEventsDto(Set<Event> events) {
        return events.stream().map(EventMapper::mapEventToEventDto).collect(Collectors.toSet());
    }

    public static Set<Event> mapEventsDtoToEvents(Set<EventDto> eventDtos) {
        return eventDtos.stream().map(EventMapper::mapEventDtoToEvent).collect(Collectors.toSet());
    }
}
