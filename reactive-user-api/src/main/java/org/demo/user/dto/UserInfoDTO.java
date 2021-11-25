package org.demo.user.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Integer userId;
    private String username;
    private String phoneNo;
    private String createTime;
}
