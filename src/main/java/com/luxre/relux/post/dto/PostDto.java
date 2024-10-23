package com.luxre.relux.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class PostDto {
	
	private int id;
    private String title;
    private String contents;
    private String imagePath;
    private String userName;
    
}
