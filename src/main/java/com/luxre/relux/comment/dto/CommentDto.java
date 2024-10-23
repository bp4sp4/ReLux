package com.luxre.relux.comment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentDto {
    private int id;
    private int postId; 
    private int userId; 
    private String userName; 
    private String contents; 
    private LocalDateTime createdAt;
}
