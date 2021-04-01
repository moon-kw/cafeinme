package com.kh.cafeinme.bookmark.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.bookmark.svc.BookmarkSVC;
import com.kh.cafeinme.member.vo.MemberVO;







@RestController
@RequestMapping("/cafeinfo")
public class BookmarkrestController {
	@Autowired
	BookmarkSVC bs;
@PostMapping("/bookmark")
public void bookmark(@RequestBody String cafe_no,HttpSession session) {

	int r = Integer.parseInt(cafe_no);


		MemberVO member = (MemberVO)session.getAttribute("members");
		
		bs.bookmark(r, member.getMember_id());
		
	}
	

@DeleteMapping("/bookmark")  
public void bookmarkdel(@RequestBody String cafe_no,HttpSession session) {

	int r = Integer.parseInt(cafe_no);
	
		MemberVO member = (MemberVO)session.getAttribute("members");
		bs.delbookmark(r, member.getMember_id());
	

}
}
