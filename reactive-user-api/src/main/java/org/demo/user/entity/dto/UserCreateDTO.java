package org.demo.user.entity.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private String phoneNo;
}
