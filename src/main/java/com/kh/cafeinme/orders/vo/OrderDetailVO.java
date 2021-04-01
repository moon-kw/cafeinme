package com.kh.cafeinme.orders.vo;

import lombok.Data;

@Data
public class OrderDetailVO {

	private int ORDER_NO;
	private int MENU_NO;
	private String MENU_NAME;
	private int MENU_COUNT;
	private long MENU_PRICE;
	
//	"ORDER_NO" NUMBER(10,0), 
//	"MENU_NO" NUMBER(10,0), 
//	"MENU_NAME" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
//	"MENU_COUNT" NUMBER(10,0) NOT NULL ENABLE, 
//	"MENU_PRICE" NUMBER(10,0) NOT NULL ENABLE, 
}
