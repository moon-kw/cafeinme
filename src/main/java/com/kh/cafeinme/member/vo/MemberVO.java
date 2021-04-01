package com.kh.cafeinme.member.vo;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {

	@NotBlank(message="필수 입력정보입니다.")
	@Email(message="아이디는 메일 형식이어야 합니다.")
	private String member_id;
	
	@NotBlank(message="필수 입력정보입니다.")
	@Size(min=4,max=10,message="비밀번호는 1~8자리 이내 영문과 숫자가 혼합되어야 합니다.")
	private String member_pw;
	
	@NotBlank(message="필수 입력정보입니다.")
	private String member_name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate member_birth;
	
	@NotBlank(message="필수 입력정보입니다.")
	private String member_nickname;
	
	@NotBlank(message="필수 입력정보입니다.")
	private String member_address1;
	
	private String member_address2;
	
	@NotBlank(message="필수 입력정보입니다.")
	@Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message="전화번호 형식이 맞지 않습니다. ex)010-1234-5678")
	private String member_tel;
}
