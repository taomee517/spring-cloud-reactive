package org.demo.user.dto;

import lombok.Data;

/**
 * DTO: Data Transfer Object
 * 数据传输对象
 */
@Data
public class UserSaveDTO {
    private String username;
    private String password;
    private String phoneNo;
}
