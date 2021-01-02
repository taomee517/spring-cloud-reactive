package org.demo.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.demo.user.entity.User;
import org.demo.user.entity.dto.UserCreateDTO;
import org.demo.user.entity.vo.UserVO;
import org.demo.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("insert")
    public Mono<Boolean> insert(@RequestBody UserCreateDTO userCreateDTO){
        User user = new User();
        BeanUtils.copyProperties(userCreateDTO,user);
        Mono<Boolean> save = userService.save(user);
        return save;
    }

    @GetMapping("getUser")
    public Mono<UserVO> getUser(@RequestParam(value = "id") Integer id){
        if(Objects.isNull(id)){
            return Mono.error(new Exception("用户Id不能为空"));
        }
        return userService.getById(id).map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).switchIfEmpty(Mono.error(new Exception("不存在该用户")));
    }
}
