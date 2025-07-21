package com.ums.email_service.repository;

import com.ums.email_service.model.EmailLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailLogRepository extends MongoRepository<EmailLog, String> {
}
// This repository interface extends MongoRepository, which provides CRUD operations for the EmailLog entity.