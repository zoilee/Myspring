package com.zoile.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zoile.simple.model.User;



@Mapper
public interface UserMapper {
	   List<User> getAllUsers();
	   User getUserById(int id); 
	   void insertUser(User user); 
	   void deleteUser(int id);
	   void updateUser(User user);
}

