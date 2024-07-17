package com.blo.userDto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostWithCommentsDto {

	private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private Integer likes;
    private Integer commentCount;
    private List<CommentDto> comments;
}
