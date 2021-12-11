package org.demo.sso.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("access")
public class LoginController {

    @PostMapping("login")
    public String login(){
        return "Hello, reactive sso!";
    }
}
