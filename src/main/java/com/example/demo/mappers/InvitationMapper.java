package com.example.demo.mappers;

import com.example.demo.dtos.InvitationDto;
import com.example.demo.models.Invitation;

import java.util.Set;
import java.util.stream.Collectors;

public class InvitationMapper {
    public static InvitationDto mapInvitationToInvitationDto(Invitation invitation) {
        if(invitation == null){
            return null;
        }

        return InvitationDto.builder()
                .id(invitation.getId())
                .event(EventMapper.mapEventToEventDtoWithoutRelations(invitation.getEvent()))
                .user(UserMapper.mapUserToUserDtoWithoutRelations(invitation.getUser()))
                .status(invitation.getStatus())
                .build();
    }

    public static Invitation mapInvitationDtoToInvitation(InvitationDto invitationDto) {
        if(invitationDto == null){
            return null;
        }

        return Invitation.builder()
                .id(invitationDto.getId())
                .event(EventMapper.mapEventDtoToEventWithoutRelations(invitationDto.getEvent()))
                .user(UserMapper.mapUserDtoToUserWithoutRelations(invitationDto.getUser()))
                .status(invitationDto.getStatus())
                .build();
    }

    public static Set<InvitationDto> mapInvitationsToInvitationsDto(Set<Invitation> invitations) {
        return invitations.stream().map(InvitationMapper::mapInvitationToInvitationDto).collect(Collectors.toSet());
    }

    public static Set<Invitation> mapInvitationsDtoToInvitations(Set<InvitationDto> invitationsDto) {
        return invitationsDto.stream().map(InvitationMapper::mapInvitationDtoToInvitation).collect(Collectors.toSet());
    }
}
