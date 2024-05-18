package com.blogApplication.PayLoads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min =4,message="User Name must be min of 4 charecter")
	private String name;
	@Email(message ="email is not valid ")
	private String email;
	@NotEmpty
	@Size(min=5,max=10,message="pwd is not less then 5 and not more then 10")
	private String password;
	@NotEmpty
	private String about;
}
