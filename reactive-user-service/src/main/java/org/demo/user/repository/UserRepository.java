package org.demo.user.repository;

import org.demo.user.pojo.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends R2dbcRepository<User, Integer> {

    Flux<User> findByUsernameLike(String keyword);

    Flux<User> findAllByUsernameContains(String keyword);
}
