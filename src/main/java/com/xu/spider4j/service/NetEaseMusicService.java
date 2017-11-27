package com.xu.spider4j.service;

import com.xu.spider4j.entity.Music;
import com.xu.spider4j.mapper.MusicMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NetEaseMusicService {
	@Autowired
	private MusicMapper musicMapper;


	public Music getMusicByCondition(String artist,String songName){
		return musicMapper.findMusicByCondition(artist, songName);
	}

	public Music getMusic(String artist){
		List<Music> listByArtist = musicMapper.findListByArtist(artist);
		int size = listByArtist.size();
		Random random = new Random();
		return  listByArtist.get(random.nextInt(size));
	}

	public String getMusicIdBySongName(String songName){
		List<String> musicBySongName = musicMapper.findMusicBySongName(songName);
		Random random = new Random();
		return musicBySongName.get(random.nextInt(musicBySongName.size()));
	}

}
