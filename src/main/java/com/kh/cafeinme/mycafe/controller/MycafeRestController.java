package com.kh.cafeinme.mycafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.mycafe.svc.MycafeSVC;

@RestController
@RequestMapping("/cafe")
public class MycafeRestController {
	@Autowired
	MycafeSVC ms;

}
