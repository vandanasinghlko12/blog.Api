package com.springboot.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
  public class UserDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String about;

}
