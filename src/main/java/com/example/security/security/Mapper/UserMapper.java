package com.example.security.security.Mapper;

import com.example.security.security.Dto.UserDto;
import com.example.security.security.Modals.User;

public class UserMapper {
    public static UserDto mapUserToDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static User mapDtoToUser(UserDto dto){
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRole()
        );
    }
}
