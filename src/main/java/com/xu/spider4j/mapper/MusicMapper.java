package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Music;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MusicMapper {
	@Select("select count(*) from music where songId = #{songId}")
	int countBySongId(@Param("songId") String songId);

	@Select("insert into music(songId,name,author,album) value (#{songId},#{name},#{author},#{album})")
	void insert(Music music);


	@Select("select * from music")
//	@Options(flushCache = Options.FlushCachePolicy.DEFAULT)
	List<Music> findAll();

	/**
	 *
	 * 其中@Options注解常用属性:
	 - flushCache:刷新缓存策略，有DEFAULT,TRUE,FALSE三种值，默认DEFAULT表示刷新查询语句的缓存
	 - useCache：默认true，表示使用缓存
	 - fetchSize：查询时的获取数量
	 - useGeneratedKeys：默认false，是否返回插入的id
	 - keyProperty：实体类id属性
	 - keyColumn：实体类属性对应数据库的字段
	 *
	 */
}
