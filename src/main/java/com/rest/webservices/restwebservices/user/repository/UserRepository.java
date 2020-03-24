package com.rest.webservices.restwebservices.user.repository;

import com.rest.webservices.restwebservices.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
