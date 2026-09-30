package com.ecommerce.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
