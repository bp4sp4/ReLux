package com.luxre.relux.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDto {
	
	private int postId;
    private String title;
    private String contents;
    private String imagePath;
    private String userName;
}
