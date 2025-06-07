package com.project.telecom.rnu.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GroupeDto {
    Long id;
    String nom;
    List<GroupMemberDto> membres;
}
