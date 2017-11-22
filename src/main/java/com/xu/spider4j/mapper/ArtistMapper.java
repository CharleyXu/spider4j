package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Artist;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ArtistMapper {
	/**
	 * 单个插入
	 * @param artist
	 */
	@Insert("INSERT INTO artist(artistId,name,alia) value (#{id},#{name},#{alia})")
	void insert(Artist artist);

	/**
	 * 批量插入
	 * @param list
	 */
	@InsertProvider(type = ArtistProvider.class,method = "batchInsert")
	void batchInsert(@Param("list") List<Artist> list);

	@Select("select * from artist")
	List<Artist> findAll();
}
