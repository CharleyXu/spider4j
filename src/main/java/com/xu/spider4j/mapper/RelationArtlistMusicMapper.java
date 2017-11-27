package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Music;
import com.xu.spider4j.entity.RelationArtlistMusic;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RelationArtlistMusicMapper {

	/**
	 * 单个插入
	 * @param relationArtlistMusic
	 */
	@Insert("INSERT IGNORE INTO relation_artlist_music(artlistId,musicId) value (#{artlistId},#{musicId})")
	void insert(RelationArtlistMusic relationArtlistMusic);


	/**
	 * 批量插入
	 * @param list
	 */
	@InsertProvider(type = RelationArtlistMusicSqlProvider.class,method = "batchInsert")
	void batchInsert(@Param("list") List<RelationArtlistMusic> list);

	/**
	 * 查询
	 * @return
	 */
	@Select("select * from relation_artlist_music")
	List<RelationArtlistMusic> findAll();
}
