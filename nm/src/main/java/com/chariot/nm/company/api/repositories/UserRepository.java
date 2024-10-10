package com.chariot.nm.company.api.repositories;

import com.chariot.nm.company.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
