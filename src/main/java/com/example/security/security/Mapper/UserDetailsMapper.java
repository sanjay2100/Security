package com.example.security.security.Mapper;

import com.example.security.security.Dto.UserDetailsDto;
import com.example.security.security.Modals.UserDetails;

public class UserDetailsMapper {
    public static UserDetails mapDtoToModal(UserDetailsDto dto){
        return new UserDetails(
                dto.getId(),
                dto.getUserId(),
                dto.getAddress(),
                dto.getDistrict(),
                dto.getState(),
                dto.getPincode(),
                dto.getMobilenumber(),
                dto.getEmail()
        );
    }

    public static UserDetailsDto mapModalToDto(UserDetails model){
        return new UserDetailsDto(
                model.getId(),
                model.getUserId(),
                model.getAddress(),
                model.getDistrict(),
                model.getState(),
                model.getPincode(),
                model.getMobilenumber(),
                model.getEmail()
        );
    }
}
