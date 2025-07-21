package com.ums.auth_service.respository;

import com.ums.auth_service.model.UserAuth;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserAuthRepository extends MongoRepository<UserAuth, String> {
    Optional<UserAuth> findByEmail(String email);
    boolean existsByEmail(String email);
}
