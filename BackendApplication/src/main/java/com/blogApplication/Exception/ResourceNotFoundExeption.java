package com.blogApplication.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundExeption extends RuntimeException {
	
	String resourcName;
	String fieldName;
	int fieldValue;
	public ResourceNotFoundExeption(String resourcName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s : %s", resourcName,fieldName,fieldValue));
		this.resourcName = resourcName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	

}
