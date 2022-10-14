package com.spring.rest.sarafan.repo;

import com.spring.rest.sarafan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
