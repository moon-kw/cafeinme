package com.kh.cafeinme.search.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.svc.MycafeSVC;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.reviews.svc.ReviewSVC;
import com.kh.cafeinme.search.svc.SearchSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
	private final SearchSVC ss;
	//3.18추가
	private final MycafeSVC ms;
	private final ReviewSVC rs;
	@ModelAttribute
	public void initData(Model model) {
		model.addAttribute("tags", ms.gettags());
	}
@GetMapping("")
public String search(@RequestParam(value = "keyword",required = false) String keyword,Model model) {
	if(keyword!=null) {
		model.addAttribute("mainsearch",keyword);
	}
	return "search/searchmain";
}

@GetMapping("/cafeinfo/{cafe_no:.+}")
public String cafeinfo(@PathVariable("cafe_no") int cafe_no,Model model
		,HttpSession session) {
	if(ss.iscafeinfo(cafe_no)) {
		
	CafeVO cafevo = ss.cafeinfo(cafe_no);
	if(session.getAttribute("members")!=null) {
	MemberVO member = (MemberVO)session.getAttribute("members");
	cafevo.setBookmark(ss.isbookmark(cafevo,member.getMember_id()));
	model.addAttribute("login","true");
	}
	Map<String,Object> map = rs.getReviewAll(cafe_no, 1);
	log.info(map.toString());
	model.addAttribute("review",map.get("reviewList"));
	model.addAttribute("pc",map.get("pc"));
	model.addAttribute("cafevo",cafevo);
	return "search/cafeinfo";
	}
	return "index";
}
}
