package org.demo.mall.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.demo.common.query.KeywordQuery;
import org.demo.mall.vo.UserSaveVO;
import org.demo.mall.vo.UserVO;
import org.demo.user.api.UserApi;
import org.demo.user.dto.UserInfoDTO;
import org.demo.user.dto.UserSaveDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("user")
@Api(tags={"用户管理接口"}, value = "UserController")
public class UserController {

    @Lazy
    @Autowired
    UserApi userApi;

    @PostMapping("register")
    @ApiOperation(value = "用户注册")
    public Mono<Boolean> register(@RequestBody UserSaveVO userVO){
        UserSaveDTO userSaveDTO = new UserSaveDTO();
        BeanUtils.copyProperties(userVO, userSaveDTO);
        return userApi.insertUser(userSaveDTO);
    }

    @GetMapping("info/{id}")
    @ApiOperation(value = "用户信息")
    public Mono<UserVO> info(@PathVariable(value = "id") Integer id){
        return userApi.getUser(id)
                .map(this::userInfoDTO2VO)
                .onErrorMap(throwable -> {
                    log.error("查询用户信息时发生异常：{}", throwable.getMessage(),throwable);
                    return new Exception(throwable.getMessage());
                });
    }


    @GetMapping("list")
    @ApiOperation(value = "用户列表")
    public Flux<UserVO> list(KeywordQuery query){
        log.info("列表查询条件：{}", query.getKeyword());
        return userApi.query(query)
                .map(userInfoDTO -> {
                    log.info("返回结果:{}", JSON.toJSONString(userInfoDTO));
                    return this.userInfoDTO2VO(userInfoDTO);
                })
                .onErrorMap(throwable -> {
                    log.error(throwable.getMessage(),throwable);
                    return new Exception(throwable.getMessage());
                });
    }


    private UserVO userInfoDTO2VO(UserInfoDTO userInfoDTO){
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userInfoDTO,userVO);
        userVO.setRegisterTime(userInfoDTO.getCreateTime());
        return userVO;
    }
}
