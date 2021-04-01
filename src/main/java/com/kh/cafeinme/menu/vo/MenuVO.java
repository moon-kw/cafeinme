package com.kh.cafeinme.menu.vo;




import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class MenuVO {
	private int menu_no;
	@NotBlank(message = "메뉴이름을 입력해주십시오")
	private String menu_name;
	@Pattern(regexp = "^[0-9]+$",message = "가격은 숫자로만 입력해주십시오")
	private int menu_price;
	@NotBlank(message = "메뉴상세설명을 입력해주십시오")
	private String menu_content;
	private int menu_category;
	private String category_name;
	private int cafe_no;
	private MultipartFile file;
	private String mfile_name;
	private String mfile_changename;
	private String mfile_size;
	private String mfile_type;
	private String mfile_path;
	private int menu_onlinesale;
	

	public String getMfile_changename() {
		return mfile_changename;
	}
	public void setMfile_changename(String mfile_changename) {
		this.mfile_changename = mfile_changename;
	}

	public int getMenu_onlinesale() {
		return menu_onlinesale;
	}
	public void setMenu_onlinesale(int menu_onlinesale) {
		this.menu_onlinesale = menu_onlinesale;
	}
	public int getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(int menu_no) {
		this.menu_no = menu_no;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}
	public String getMenu_content() {
		return menu_content;
	}
	public void setMenu_content(String menu_content) {
		this.menu_content = menu_content;
	}
	public int getMenu_category() {
		return menu_category;
	}
	public void setMenu_category(int menu_category) {
		this.menu_category = menu_category;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getCafe_no() {
		return cafe_no;
	}
	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getMfile_name() {
		return mfile_name;
	}
	public void setMfile_name(String mfile_name) {
		this.mfile_name = mfile_name;
	}
	public String getMfile_size() {
		return mfile_size;
	}
	public void setMfile_size(String mfile_size) {
		this.mfile_size = mfile_size;
	}
	public String getMfile_type() {
		return mfile_type;
	}
	public void setMfile_type(String mfile_type) {
		this.mfile_type = mfile_type;
	}
	public String getMfile_path() {
		return mfile_path;
	}
	public void setMfile_path(String mfile_path) {
		this.mfile_path = mfile_path;
	}



}
