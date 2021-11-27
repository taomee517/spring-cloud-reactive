package org.demo.user.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping("hello")
    @ApiOperation(value = "测试")
    public Mono<String> hello(@RequestParam(value = "any") String any){
        String regret = StringUtils.joinWith(",", "Hello", any);
        return Mono.just(regret);
    }
}
