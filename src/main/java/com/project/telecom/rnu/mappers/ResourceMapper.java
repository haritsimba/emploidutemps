package com.project.telecom.rnu.mappers;

import com.project.telecom.rnu.dtos.ResourceDto;
import com.project.telecom.rnu.dtos.UserDto;
import com.project.telecom.rnu.entities.Resource;
import com.project.telecom.rnu.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ResourceMapper {
    public static ResourceDto mapToDto(Resource resource){
        ResourceDto dto = new ResourceDto();
        dto.setId(resource.getId());
        dto.setLibelle(resource.getLibelle());
        return dto;
    }

    public static List<ResourceDto> mapListToDto(List<Resource> resources){
        ArrayList<ResourceDto> dtos = new ArrayList<>();
        for (Resource r : resources){
            dtos.add(mapToDto(r));
        }
        return dtos;
    }
}
