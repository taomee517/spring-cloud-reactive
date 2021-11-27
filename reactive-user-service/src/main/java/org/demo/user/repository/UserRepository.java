package org.demo.user.repository;

import org.demo.user.pojo.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface UserRepository extends R2dbcRepository<User, Integer> {

    @Query(value = "SELECT user_id, username, phone_no, create_time FROM tb_user WHERE username like :keyword")
    Flux<User> findByUsernameLike(@Param("keyword")String keyword);

    Flux<User> findAllByUsernameContains(String keyword);
}
