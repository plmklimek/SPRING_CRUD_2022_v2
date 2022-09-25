package com.example.demo.mappers;

import com.example.demo.dtos.EventDto;
import com.example.demo.models.Event;

public class EventMapper {
    public static Event mapEventDtoToEvent(EventDto eventDto) {
        if(eventDto == null){
            return null;
        }

        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .invitations(InvitationMapper.mapInvitationsDtoToInvitations(eventDto.getInvitations()))
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
                .build();
    }

    public static Event mapEventDtoToEventWithoutRelations(EventDto eventDto) {
        if(eventDto == null){
            return null;
        }

        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .build();
    }

    public static EventDto mapEventToEventDtoWithoutRelations(Event event) {
        if(event == null){
            return null;
        }

        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .build();
    }
}
