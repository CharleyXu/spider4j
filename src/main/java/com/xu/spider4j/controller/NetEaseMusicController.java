package com.xu.spider4j.controller;

import com.xu.spider4j.entity.Music;
import com.xu.spider4j.service.NetEaseMusicService;

import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetEaseMusicController {
	@Autowired
	private NetEaseMusicService netEaseMusicService;

	@GetMapping("/netEaseMusic")
	public String musicList(String artist ,String name){
		if(Strings.isNullOrEmpty(artist)){
			return netEaseMusicService.getMusicIdBySongName(name);
		}else if (Strings.isNullOrEmpty(name)){
			return netEaseMusicService.getMusic(artist).toString();
		}
		return netEaseMusicService.getMusicByCondition(artist, name).toString();
	}

	@RequestMapping("/netEaseMusic/comment")
	public String commentList(String name){

		return null;
	}

}
