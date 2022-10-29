package com.example.demo.mappers;

import com.example.demo.dtos.InvitationDto;
import com.example.demo.models.Invitation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class InvitationMapper {
    public static InvitationDto mapInvitationToInvitationDto(Invitation invitation) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(invitation, InvitationDto.class);
    }

    public static Invitation mapInvitationDtoToInvitation(InvitationDto invitationDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(invitationDto, Invitation.class);
    }

    public static List<InvitationDto> mapInvitationsToInvitationsDto(List<Invitation> invitations) {
        return invitations.stream().map(InvitationMapper::mapInvitationToInvitationDto).collect(Collectors.toList());
    }

    public static List<Invitation> mapInvitationsDtoToInvitations(List<InvitationDto> invitationsDto) {
        return invitationsDto.stream().map(InvitationMapper::mapInvitationDtoToInvitation).collect(Collectors.toList());
    }
}
