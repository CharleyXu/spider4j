package com.xu.spider4j.pipeline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xu.spider4j.entity.Artist;
import com.xu.spider4j.entity.Music;
import com.xu.spider4j.entity.RelationArtlistMusic;
import com.xu.spider4j.mapper.ArtistMapper;
import com.xu.spider4j.mapper.MusicMapper;
import com.xu.spider4j.mapper.RelationArtlistMusicMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Component
/**
 * 自定义Pipeline
 * 整合Mybatis,抽取结果存入Mysql
 */
public class NetEaseCloudMusicPipeline implements Pipeline{
	@Autowired
	private ArtistMapper artistMapper;
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private RelationArtlistMusicMapper relationArtlistMusicMapper;

	// ResultItems保存了抽取结果，它是一个Map结构，
	// 在page.putField(key,value)中保存的数据，可以通过ResultItems.get(key)获取
	@Override
	public void process(ResultItems resultItems, Task task) {
		JSONArray jsonArray = JSONArray.parseArray(resultItems.get("result"));
		System.out.println("size:"+jsonArray.size()+"\n"+jsonArray.toJSONString());
		//处理歌手信息	artistList
		List<Artist> artistList = new ArrayList<>();
		//处理音乐信息	批量
		List<Music> musicList = new ArrayList<>();
		//处理歌手_音乐关系_信息
		List<RelationArtlistMusic> relationArtlistMusicList = new ArrayList<>();

		for (int i = 0,size = jsonArray.size(); i <size ; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int musicId = jsonObject.getInteger("id");//音乐Id
			String name = jsonObject.getString("name");//音乐名称
			int score = jsonObject.getInteger("score");//热度
			String album = jsonObject.getJSONObject("album").getString("name");//专辑名称
			String commentThreadId = jsonObject.getString("commentThreadId");//评论请求Id

			JSONArray artists = jsonObject.getJSONArray("artists");

			List<Artist> tempList = JSON.parseArray(artists.toJSONString(), Artist.class);//临时ArtistList
			for (int j = 0,length = tempList.size(); j < length; j++) {
				int artistId = tempList.get(j).getId();
				RelationArtlistMusic relationArtlistMusic = new RelationArtlistMusic(artistId,musicId);
				relationArtlistMusicList.add(relationArtlistMusic);
			}
			artistList.addAll(tempList);

			Music music = new Music(musicId,name,score,album,commentThreadId);
			System.out.println(music);
			musicList.add(music);
		}
		//		artistMapper.batchInsert(artist);
		//		musicMapper.batchInsert(musicList);
		//		relationArtlistMusicMapper.batchInsert(relationArtlistMusicList);
	}
}
