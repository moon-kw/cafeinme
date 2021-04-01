package com.kh.cafeinme.reviews.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommentVO {

	private long COMMENT_NO;
	private String COMMENT_CONTENT;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp COMMENT_DATE;
	private int CAFE_NO;
	private long REVIEW_NO;
}
