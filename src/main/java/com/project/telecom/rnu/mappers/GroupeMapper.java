package com.project.telecom.rnu.mappers;

import com.project.telecom.rnu.dtos.GroupMemberDto;
import com.project.telecom.rnu.dtos.GroupeDto;
import com.project.telecom.rnu.entities.Groupe;
import com.project.telecom.rnu.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class GroupeMapper {
    public static GroupeDto mapToDto(Groupe groupe){
        GroupeDto dto = new GroupeDto();
        List< GroupMemberDto> gmDtos = new ArrayList<>();
        dto.setId(groupe.getId());
        dto.setNom(groupe.getNom());
        for (Student s : groupe.getStudents()){
            GroupMemberDto gmDto = new GroupMemberDto();
            gmDto.setId(s.getId());
            gmDto.setUsername(s.getUsername());
            gmDtos.add(gmDto);
        }
        dto.setMembres(gmDtos);
        return dto;
    }

    public static List<GroupeDto> mapListToDto(List<Groupe> groupes){
        List<GroupeDto> dtos = new ArrayList<>();
        for (Groupe g : groupes){
            dtos.add(mapToDto(g));
        }
        return dtos;
    }
}
