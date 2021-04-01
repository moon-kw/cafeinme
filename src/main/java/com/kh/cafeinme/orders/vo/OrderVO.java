package com.kh.cafeinme.orders.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderVO {

	private int ORDER_NO;
	private String MEMBER_ID;
	private int CAFE_NO;
	private String CAFE_NAME;
	private String ORDER_ITEMS;
	private long ORDER_PRICE;
	private int ORDER_COUNT;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp ORDER_DATE;
	private String ORDER_ADDRESS1;
	private String ORDER_ADDRESS2;
	private String ORDER_PAYMENTTYPE;
	private String ORDER_RECEIVETYPE;
	private String ORDER_STATUS;
	private int ORDER_REVIEW_YN;
	
	
//	"ORDER_NO" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
//	"MEMBER_ID" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"CAFE_NO" NUMBER(10,0) NOT NULL ENABLE, 
//	"ORDER_PRICE" NUMBER(10,0) NOT NULL ENABLE, 
//	"ORDER_DATE" DATE NOT NULL ENABLE, 
//	"ORDER_ADDRESS1" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
//	"ORDER_ADDRESS2" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
//	"ORDER_STATUS" VARCHAR2(15 BYTE) NOT NULL ENABLE, 
}
