package com.blo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponse {

	String fileName;
	String message;
	public FileResponse(String fileName, String message) {
		
		this.fileName = fileName;
		this.message = message;
	}
	
}
