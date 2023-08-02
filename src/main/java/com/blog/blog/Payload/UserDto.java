package com.blog.blog.Payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int Id;
	@NotEmpty
	@Size(min = 4,message = "Name must be more than 4  character")
	private String name;
	@Email(message = "Email adress must be valis")
	private String email;
	@NotEmpty
	@Size(min = 3,max=10,message ="Password must be more than 3 and less than 10 chars" )
	private String password;
	@NotEmpty
	private String about;
}
