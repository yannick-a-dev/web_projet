package com.webproject.webproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.webproject.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
