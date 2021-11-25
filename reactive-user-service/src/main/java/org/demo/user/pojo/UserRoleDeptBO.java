package org.demo.user.pojo;

import lombok.Data;

/**
 * BO Business Object 用户信息中不应该出现密码
 */
@Data
public class UserRoleDeptBO {
    private Integer userId;
    private String username;
    private String phoneNo;
}
