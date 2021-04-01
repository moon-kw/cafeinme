package com.kh.cafeinme.reviews.vo;

import java.sql.Timestamp;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReviewVO {
// 3.17추가
	private String CAFE_NAME;
	private long REVIEW_NO;					//	"REVIEW_NO" NUMBER(10,0) DEFAULT "C##CAFEINME"."REVIEWS_REVIEW_NO_SEQ"."NEXTVAL", 
//	private String REVIEW_TITLE;			//	"REVIEW_TITLE" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	private String REVIEW_NICKNAME;
	private String REVIEW_ITEMS;
	private String REVIEW_CONTENT;			//	"REVIEW_CONTENT" CLOB NOT NULL ENABLE, 
	private int REVIEW_STAR;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp REVIEW_DATE;			//	"REVIEW_DATE" TIMESTAMP (6) DEFAULT SYSTIMESTAMP NOT NULL ENABLE, 
	private Integer REVIEW_CMT;
	private String MEMBER_ID;				//	"MEMBER_ID" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	private int CAFE_NO;					//	"CAFE_NO" NUMBER(10,0) NOT NULL ENABLE, 
	private int ORDER_NO;
	
	@Valid
	private CommentVO commentVO;
	
	private long COMMENT_NO;
	private String COMMENT_CONTENT;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp COMMENT_DATE;
}
