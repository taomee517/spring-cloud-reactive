package org.demo.user.service.impl;

import org.demo.user.entity.User;
import org.demo.user.repository.UserRepository;
import org.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Mono<Boolean> save(User user) {
        return userRepository.save(user)
                .log()
                .flatMap(u->{
                   if(Objects.nonNull(u)){
                       return Mono.just(Boolean.TRUE);
                   }else {
                       return Mono.just(Boolean.FALSE);
                   }
                });
    }

    @Override
    public Mono<User> getById(int userId) {
        return userRepository.findById(userId);
    }
}
