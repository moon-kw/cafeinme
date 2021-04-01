package com.kh.cafeinme.orders.vo;

import lombok.Data;

@Data
public class CartVO {

	private String MEMBER_ID;
	private int CAFE_NO;
	private int MENU_NO;
	private String MENU_NAME;
	private int MENU_COUNT;
	private long MENU_PRICE;
	
//	"MEMBER_ID" VARCHAR2(30 BYTE), 
//	"CAFE_NO" NUMBER(10,0) NOT NULL ENABLE, 
//	"MENU_NO" NUMBER(10,0) NOT NULL ENABLE, 
//	"MENU_NAME" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
//	"MENU_COUNT" NUMBER(10,0) NOT NULL ENABLE, 
//	"MENU_PRICE" NUMBER(10,0) NOT NULL ENABLE, 
}
