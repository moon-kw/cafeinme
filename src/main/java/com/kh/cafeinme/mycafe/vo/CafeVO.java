package com.kh.cafeinme.mycafe.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.kh.cafeinme.menu.vo.MenuVO;

import lombok.Data;

@Data
public class CafeVO {
	

	private List<Integer> tag_no;
	private List<String> tag_name;
	
	private int CAFE_NO;			//	"CAFE_NO" NUMBER(10,0) DEFAULT "C##CAFEINME"."CAFE_CAFE_NO_SEQ"."NEXTVAL", 
	private String CAFE_OWNERID;	//	"CAFE_OWNERID" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "카페이름을 입력해주십시오.")
	@Size(min = 1,max = 20, message = "카페이름은 20자를 초과 할 수 없습니다." )
	private String CAFE_NAME;		//	"CAFE_NAME" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "사업자번호를 입력해주십시오")
	@Size(min = 1,max = 12, message = "사업자번호는 12자리를 초과 할 수 없습니다." )
	@Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "사업자번호 형식이 맞지 않습니다. ex)000-00-00000")
	private String BN;				//	"BN" VARCHAR2(12 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "주소를 입력해주십시오")
	private String CAFE_ADDRESS1;	//	"CAFE_ADDRESS1" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	private String CAFE_ADDRESS2;	//	"CAFE_ADDRESS2" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "전화 번호를 입력해주십시오")
	private String CAFE_TEL;		//	"CAFE_TEL" VARCHAR2(13 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "여는시간을 입력해주십시오")
	private String OPEN_TIME;		//	"OPEN_TIME" VARCHAR2(7 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "카페 소개를 입력해주십시오")
	private String CLOSE_TIME;		//	"CLOSE_TIME" VARCHAR2(7 BYTE) NOT NULL ENABLE, 
	
	@NotBlank(message = "카페 소개를 입력해주십시오")
	private String CAFE_CONTENT;	//	"CAFE_CONTENT" VARCHAR2(300 BYTE) NOT NULL ENABLE, 
	
	
	private List<MultipartFile> files;
	private List<CfileVO> cfilevo;
	private List<MenuVO> menuvo;
	private String cfile_path;
	private String tagnames;
	
	private boolean bookmark;
	private int cafe_onlinesale;
	
	public List<MenuVO> getMenuvo() {
		return menuvo;
	}
	public void setMenuvo(List<MenuVO> menuvo) {
		this.menuvo = menuvo;
	}
	public String getTagnames() {
		return tagnames;
	}
	public void setTagnames(String tagnames) {
		this.tagnames = tagnames;
	}
	public String getCfile_path() {
		return cfile_path;
	}
	public void setCfile_path(String cfile_path) {
		this.cfile_path = cfile_path;
	}
	public int getCafe_no() {
		return CAFE_NO;
	}
	public void setCafe_no(int cafe_no) {
		this.CAFE_NO = cafe_no;
	}
	public String getCafe_ownerid() {
		return CAFE_OWNERID;
	}
	public void setCafe_ownerid(String cafe_ownerid) {
		this.CAFE_OWNERID = cafe_ownerid;
	}
	public String getCafe_name() {
		return CAFE_NAME;
	}
	public void setCafe_name(String cafe_name) {
		this.CAFE_NAME = cafe_name;
	}
	public String getBn() {
		return BN;
	}
	public void setBn(String bn) {
		this.BN = bn;
	}
	public String getCafe_address1() {
		return CAFE_ADDRESS1;
	}
	public void setCafe_address1(String cafe_address1) {
		this.CAFE_ADDRESS1 = cafe_address1;
	}
	public String getCafe_address2() {
		return CAFE_ADDRESS2;
	}
	public void setCafe_address2(String cafe_address2) {
		this.CAFE_ADDRESS2 = cafe_address2;
	}
	public String getCafe_tel() {
		return CAFE_TEL;
	}
	public void setCafe_tel(String cafe_tel) {
		this.CAFE_TEL = cafe_tel;
	}
	public String getOpen_time() {
		return OPEN_TIME;
	}
	public void setOpen_time(String open_time) {
		this.OPEN_TIME = open_time;
	}
	public String getClose_time() {
		return CLOSE_TIME;
	}
	public void setClose_time(String close_time) {
		this.CLOSE_TIME = close_time;
	}
	public String getCafe_content() {
		return CAFE_CONTENT;
	}
	public void setCafe_content(String cafe_content) {
		this.CAFE_CONTENT = cafe_content;
	}
	public List<Integer> getTag_no() {
		return tag_no;
	}
	public void setTag_no(List<Integer> tag_no) {
		this.tag_no = tag_no;
	}
	public List<String> getTag_name() {
		return tag_name;
	}
	public void setTag_name(List<String> tag_name) {
		this.tag_name = tag_name;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public List<CfileVO> getCfilevo() {
		return cfilevo;
	}
	public void setCfilevo(List<CfileVO> cfilevo) {
		this.cfilevo = cfilevo;
	}
	public boolean isBookmark() {
		return bookmark;
	}
	public void setBookmark(boolean bookmark) {
		this.bookmark = bookmark;
	}
	
	
	
	
	
}
