package com.project.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.main.domain.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
 
}
