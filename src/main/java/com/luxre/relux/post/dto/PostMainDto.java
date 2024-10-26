package com.luxre.relux.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostMainDto {
    private String userName;
    private String imagePath;
    private int views;
}
