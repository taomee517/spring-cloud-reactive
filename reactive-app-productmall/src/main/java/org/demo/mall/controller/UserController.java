package org.demo.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.demo.user.api.UserApi;
import org.demo.user.entity.dto.UserCreateDTO;
import org.demo.user.entity.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("user")
@Api(tags={"用户管理接口"}, value = "UserController")
public class UserController {

    @Autowired
    UserApi userApi;

    @PostMapping("register")
    @ApiOperation(value = "用户注册")
    public Mono<Boolean> register(@RequestBody UserCreateDTO userCreateDTO){
        Mono<Boolean> booleanMono = userApi.insertUser(userCreateDTO);
        return booleanMono;
    }

    @GetMapping("info/{id}")
    @ApiOperation(value = "用户信息")
    public Mono<UserVO> info(@PathVariable(value = "id") Integer id){
        return userApi.getUser(id)
                .onErrorMap(throwable -> {
                    log.error(throwable.getMessage(),throwable);
                    return new Exception(throwable.getMessage());
                });
    }
}
