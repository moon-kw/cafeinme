package com.kh.cafeinme.menu.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryVO {

//	CATEGORY_NO	NUMBER(4,0)	No	"C##CAFEINME"."CATEGORYS_CATEGORY_NO_SEQ"."NEXTVAL"	1	
//	CATEGORY_NAME	VARCHAR2(30 BYTE)	No		2	
	
	@NotNull(message = "분류를 선택해 주세요!")
	private int CATEGORY_NO;
	private String CATEGORY_NAME;
}
