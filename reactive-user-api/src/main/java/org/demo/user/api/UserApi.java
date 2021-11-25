package org.demo.user.api;

import org.demo.common.query.KeywordQuery;
import org.demo.user.dto.UserInfoDTO;
import org.demo.user.dto.UserSaveDTO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "USER-SERVICE")
public interface UserApi {

    @GetMapping("/user/query")
    Flux<UserInfoDTO> query(@SpringQueryMap KeywordQuery keyword);

    @PostMapping("/user/insert")
    Mono<Boolean> insertUser(@RequestBody UserSaveDTO userDTO);

    @GetMapping("/user/info")
    Mono<UserInfoDTO> getUser(@RequestParam(value = "id") Integer id);
}
