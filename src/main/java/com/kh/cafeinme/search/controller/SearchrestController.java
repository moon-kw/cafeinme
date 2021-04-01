package com.kh.cafeinme.search.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.search.svc.SearchSVC;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchrestController {

	private final SearchSVC ss;

@GetMapping("/road/{keyword:.+}")
public List<CafeVO> searchroad(@PathVariable("keyword") String keyword){
	
	return ss.searchroad(keyword);
}
@GetMapping("/cafename/{cafename:.+}")
public List<CafeVO> searchname(@PathVariable("cafename") String keyword){
	
	return ss.searchname(keyword);
}

}
