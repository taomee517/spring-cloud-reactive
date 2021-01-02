package org.demo.user.service;

import org.demo.user.entity.User;
import org.demo.user.entity.dto.UserCreateDTO;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<Boolean> save(User user);

    Mono<User> getById(int userId);
}
