package org.demo.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.demo.common.query.KeywordQuery;
import org.demo.user.dto.UserInfoDTO;
import org.demo.user.dto.UserSaveDTO;
import org.demo.user.pojo.User;
import org.demo.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "user")
@Api(tags={"用户服务接口"}, value = "UserController")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("query")
    @ApiOperation(value = "用户列表")
    public Flux<UserInfoDTO> query(KeywordQuery keyword){
        return userService.query(keyword).map(this::userPO2InfoDTO);
    }

    @PostMapping("insert")
    @ApiOperation(value = "用户添加")
    public Mono<Boolean> insert(@RequestBody UserSaveDTO saveDTO){
        User user = new User();
        BeanUtils.copyProperties(saveDTO, user);
//        Date now = new Date();
//        Instant now = Instant.now();
        LocalDate now = LocalDate.now();
        String opUser = "admin";
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setCreateUser(opUser);
        user.setUpdateUser(opUser);
        Mono<Boolean> save = userService.save(user);
        return save;
    }

    @GetMapping("info")
    @ApiOperation(value = "用户详情")
    public Mono<UserInfoDTO> getUser(@RequestParam(value = "id") Integer id){
        if(Objects.isNull(id)){
            return Mono.error(new Exception("用户Id不能为空"));
        }
        return userService.getById(id)
                .map(this::userPO2InfoDTO)
                .switchIfEmpty(Mono.error(new Exception("不存在该用户")));
    }

    private UserInfoDTO userPO2InfoDTO(User user){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);
        LocalDate createTime = user.getCreateTime();
        userInfoDTO.setCreateTime(createTime.toString());
        return userInfoDTO;
    }
}
