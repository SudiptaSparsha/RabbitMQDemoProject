package com.selfbetterment.springboot.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
