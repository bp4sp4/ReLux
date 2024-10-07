package com.qwerpark.memo.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CardView {
	
	
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	
	private String loginId;
}
