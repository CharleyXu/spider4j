package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Music;
import com.xu.spider4j.entity.PageRequest;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MusicMapper {
	@Select("select count(*) from music where musicId = #{musicId}")
	@Results({@Result(property = "id", column = "musicId")})
	int countBySongId(@Param("songId") int musicId);

	/**
	 * 单个插入	避免重复插入
	 * (根据主键或者唯一索引判断)，如果数据库没有数据，就插入新的数据，如果有数据的话就跳过这条数据.
	 * 不影响AUTO_INCREMENT
	 * @param music
	 */
	@Insert("INSERT IGNORE INTO music(musicId,name,album,score,commentThreadId) value (#{id},#{name},#{album},#{score},#{commentThreadId})")
	void insert(Music music);

	/**
	 * 批量插入
	 * @param list
	 */
	@InsertProvider(type = MusicSqlProvider.class,method = "batchInsert")
	void batchInsert(@Param("list") List<Music> list);

	/**
	 * 查询
	 * @return
	 */
	@Select("select * from music")
//	@Options(flushCache = Options.FlushCachePolicy.DEFAULT)
	List<Music> findAll();

	/**
	 * 分页查询
	 * @param request
	 * @return
	 */
	@SelectProvider(type = MusicSqlProvider.class, method = "findByPage")
	@Results({@Result(property = "id", column = "musicId")})
	List<Music> findByPage(@Param("page") PageRequest request);

	/**
	 * 查询数量
	 * @return
	 */
	@Select("select count(*) from music")
	int countSize();
}
