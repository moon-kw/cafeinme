package com.kh.cafeinme.reviews.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.common.svc.CodeSVC;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.reviews.svc.ReviewSVC;
import com.kh.cafeinme.reviews.vo.CommentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class RestReviewController {

	private final ReviewSVC reviewSVC;
	private final CodeSVC codeSVC;
	// 3.18 추가
	@GetMapping({"/myreview/{start:.+}/{end:.+}/{reqpage:.+}","/myreview/{start:.+}/{end:.+}"})
	public Map<String,Object> getmyreviewwhen(
			@PathVariable("start") Date start,
			@PathVariable("end") Date end,
			@PathVariable(value="reqpage",required = false) Optional<Integer> reqpage,
			HttpSession session)
	{

		MemberVO member = (MemberVO)session.getAttribute("members");
		Map<String,Object> map = reviewSVC.getmyreviewwhen(start.toString(), end.toString(), member.getMember_id(), reqpage.orElse(1));
		map.put("startday", start.toString());
		map.put("endday", end.toString());
		return map;
	}
	
	@GetMapping("/cafereview/{cafe_no:.+}/{reqpage:.+}")
	public Map<String,Object> getcafereview(
			@PathVariable("cafe_no") int cafe_no,
			@PathVariable("reqpage") int reqpage){	
		return reviewSVC.getReviewAll(cafe_no, reqpage);
		
	}

	//리뷰 등록 처리
	@PostMapping("/writeReview")
	public Map<String, Object> writeReview(HttpSession session, @RequestBody Map<String,String> reqData){
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		Map<String, Object> map = reviewSVC.registReview(memberVO, reqData);
		return map;
	}

	//리뷰 1개 가져오기
	@GetMapping("/getOneReview/{order_no}")
	public Map<String, Object> getOneReview(@PathVariable("order_no") int order_no){
		Map<String, Object> map = reviewSVC.getOneReview(order_no);
		return map;
	}
	//리뷰 수정
	@PostMapping("/modifyReview")
	public Map<String, Object> modifyReview(@RequestBody Map<String,String> reqData){
		Map<String, Object> map = reviewSVC.modifyReview(reqData);
		return map;
	}
	
	//댓글 등록
	@PostMapping("/registComment")
	public Map<String, Object> registComment(HttpSession session, @RequestBody Map<String,String> reqData){
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		reqData.put("member_id", memberVO.getMember_id());
		Map<String, Object> map = reviewSVC.registComment(reqData);
		return map;
	}
	//댓글 수정
	@PostMapping("/modifyComment")
	public Map<String, Object> modifyComment(@RequestBody Map<String,String> reqData){
		Map<String, Object> map = reviewSVC.modifyComment(reqData);
		return map;
	}
}
