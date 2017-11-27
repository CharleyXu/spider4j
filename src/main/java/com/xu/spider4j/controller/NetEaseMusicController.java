package com.xu.spider4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetEaseMusicController {


	@GetMapping("/netEaseMusic/list")
	public String musicList(){
		String result = null ;

		return result;
	}

}
