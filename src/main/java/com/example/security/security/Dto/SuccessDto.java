package com.example.security.security.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SuccessDto {
    private String message;

    public SuccessDto(String message){
        this.message=message;
    }
}
