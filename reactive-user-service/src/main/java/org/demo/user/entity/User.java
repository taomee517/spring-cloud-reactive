package org.demo.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "tb_user")
public class User {

    @Id
    private Integer userId;
    private String username;
    private String password;
    private String phoneNo;
}
