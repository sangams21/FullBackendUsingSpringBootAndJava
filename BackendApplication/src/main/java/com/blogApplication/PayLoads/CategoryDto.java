package com.blogApplication.PayLoads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int cId;
	@NotEmpty(message ="can not be not null ")
	@Size(min=4)
	private String cTitle;
	@Size(min =4)
	@NotEmpty(message ="can not be not null ")
	private String cDiscription;
	

}
