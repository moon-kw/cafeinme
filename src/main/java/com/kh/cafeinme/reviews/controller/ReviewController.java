package com.kh.cafeinme.reviews.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.cafeinme.common.paging.PageCriteria;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.reviews.svc.ReviewSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping({"/shop","/reviews"})
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewSVC reviewSVC;
	
	/**
	 * 내카페 리뷰보기
	 * @return
	 */
	//3.24수정
	@GetMapping({"/commentWriteForm","/commentWriteForm/{recodenum:.+}"})
	public String commentWriteForm(Model model,
			HttpSession session,
			@PathVariable(value="recodenum",required = false) Optional<Integer> recodenum) {
		MemberVO member = (MemberVO)session.getAttribute("members");
		Map<String,Object> map = reviewSVC.getneedwritereview(member.getMember_id(), recodenum.orElse(1));
		log.info("맵:"+map);
		model.addAttribute("review",map.get("review"));
		model.addAttribute("pc",map.get("pc"));
		return "reviews/myCafeReview";
	}
	
	/**
	 * 내가 쓴 리뷰보기
	 * @return
	 */
	//3.12 추가
	@GetMapping({"/myreview","/myreview/{reqpage:.+}"})
	public String myreview(HttpSession session,
			@PathVariable(value="reqpage",required = false) 
	Optional<Integer> reqpage,
	Model model) {
		MemberVO member = (MemberVO)session.getAttribute("members");
		Map<String,Object> map = reviewSVC.getReviewAll(member.getMember_id(),reqpage.orElse(1));
		model.addAttribute("review",map.get("reviewList"));
		model.addAttribute("pc",(PageCriteria)map.get("pc"));
		return "reviews/myReviewList";
	}
}
