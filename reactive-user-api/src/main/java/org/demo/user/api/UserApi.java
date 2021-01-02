package org.demo.user.api;

import org.demo.user.entity.dto.UserCreateDTO;
import org.demo.user.entity.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "USER-SERVICE")
public interface UserApi {

    @PostMapping("/user/insert")
    Mono<Boolean> insertUser(UserCreateDTO userCreateDTO);

    @GetMapping("/user/getUser")
    Mono<UserVO> getUser(@RequestParam(value = "id") Integer id);
}
