package com.example.demo.mappers;

import com.example.demo.dtos.InvitationDto;
import com.example.demo.models.Invitation;

import java.util.List;
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

    public static InvitationDto mapInvitationToInvitationDtoWithoutRelation(Invitation invitation) {
        if(invitation == null){
            return null;
        }

        return InvitationDto.builder()
                .id(invitation.getId())
                .status(invitation.getStatus())
                .build();
    }

    public static Invitation mapInvitationDtoToInvitationWithoutRelation(InvitationDto invitationDto) {
        if(invitationDto == null){
            return null;
        }

        return Invitation.builder()
                .id(invitationDto.getId())
                .status(invitationDto.getStatus())
                .build();
    }


    public static List<InvitationDto> mapInvitationsToInvitationsDto(List<Invitation> invitations) {
        return invitations.stream().map(InvitationMapper::mapInvitationToInvitationDto).collect(Collectors.toList());
    }

    public static List<Invitation> mapInvitationsDtoToInvitations(List<InvitationDto> invitationsDto) {
        return invitationsDto.stream().map(InvitationMapper::mapInvitationDtoToInvitation).collect(Collectors.toList());
    }
}
