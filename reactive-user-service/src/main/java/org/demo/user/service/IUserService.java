package org.demo.user.service;

import org.demo.common.query.KeywordQuery;
import org.demo.user.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<Boolean> save(User user);

    Mono<User> getById(Integer userId);

    Flux<User> query(KeywordQuery query);
}
