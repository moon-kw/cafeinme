package com.kh.cafeinme.menu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.menu.svc.MenuSVC;
import com.kh.cafeinme.menu.vo.OnlineVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuRestController {
	@Autowired
	MenuSVC ms;
	@Autowired
  private HttpSession httpSession;
	
	/**
	 * 내 카페 삭제
	 * @param menu_no
	 */
	@DeleteMapping("/del")
	public void delete(@RequestBody int menu_no) {
		MemberVO member = (MemberVO)httpSession.getAttribute("members");
		ms.delmenu(menu_no,member.getMember_id());
	}
	@PatchMapping("/online")
	public void update(@RequestBody OnlineVO on) {
		ms.updateonline(on.getMenu_no(), on.getStatus());
	}
}
