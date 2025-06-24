package com.springboot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
