package com.example.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto{
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="please enter valid name it shoud be more than 4 characters")
    private String name;
	
	@Email(message = "Email address not valid")
	private String email;
	
	@NotEmpty
	@Size(min=4,max=10,message = "invalid Password ! it shoud be min 4 & max 10 characters")
	private String password;
	
	@NotNull
	@Size(min=10,message="invalid count of char")
	private String about;
	
	
}
