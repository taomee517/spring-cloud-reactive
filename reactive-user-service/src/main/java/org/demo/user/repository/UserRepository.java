package org.demo.user.repository;

import org.demo.user.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<User, Integer> {
}
