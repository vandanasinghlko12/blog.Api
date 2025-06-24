package com.springboot.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entities.User;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.payload.UserDto;
import com.springboot.blog.repositories.UserRepository;
import com.springboot.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userrepository;

	@Override
	public UserDto createUser(UserDto userdto) {
		
	User user	=this.dtoToUser(userdto);
   User saveduser	=this.userrepository.save(user);
		
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
	
	User user=this.userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
	
	user.setName(userdto.getName());
	user.setEmail(userdto.getEmail());
	user.setPassword(userdto.getPassword());
	user.setAbout(userdto.getAbout());
	
    User updatedUser=this.userrepository.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
	User user	=this. userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
  List<User> users  = this.userrepository.findAll();
  
  List<UserDto> userdtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
   User user	=this.userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
    this.userrepository.delete(user);
		
	}
	
	
	public User dtoToUser(UserDto userdto) {
		User user=new User();
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userdto=new UserDto();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		userdto.setAbout(user.getAbout());
		return userdto;
		
	}

}
