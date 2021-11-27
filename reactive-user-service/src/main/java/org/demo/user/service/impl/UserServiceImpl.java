package org.demo.user.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.demo.common.query.KeywordQuery;
import org.demo.user.pojo.User;
import org.demo.user.repository.UserRepository;
import org.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
    public Mono<User> getById(Integer userId) {
        return userRepository.findById(userId);
    }


    @Override
    public Flux<User> query(KeywordQuery query) {
//        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "user_id"))
//                .filter(user -> StringUtils.contains(user.getUsername(), query.getKeyword()));
//        return userRepository.findAllByUsernameContains(query.getKeyword());
        return userRepository.findByUsernameLike(StringUtils.join("%", query.getKeyword(), "%"));
    }
}
