package com.kh.cafeinme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.cafeinme.common.paging.PageCriteria;
import com.kh.cafeinme.common.paging.RecordCriteria;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Config {
	
	static final int REC = 10;
	static final int PAGE = 10;
	
	@Bean
	public RecordCriteria rc_10() {
		return new RecordCriteria(REC);
	}
	@Bean
	public PageCriteria pc_1() {
		return new PageCriteria(new RecordCriteria(3),PAGE);
	} 
	@Bean
	public PageCriteria pc_10() {
		return new PageCriteria(rc_10(), PAGE);
	}
	
}
