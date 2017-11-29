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
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MusicMapper {
	@Select("select count(1) from music where musicId = #{musicId}")
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
	@Select("select count(1) from music")
	int countSize();

	/**
	 *实际业务
	 *
	 * 按照歌手和歌曲名称查询
	 * 注意：歌手别名
	 */
	@Select("SELECT a.musicId,a.name FROM music a LEFT JOIN relation_artlist_music b on a.musicId = b.musicId WHERE a.`name`=#{name} and b.artistId = (" +
			"SELECT artistId FROM artist c WHERE c.name = #{artist} OR c.alia LIKE CONCAT(CONCAT('%', #{artist}), '%') LIMIT 1)")
	@Results({@Result(property = "id", column = "musicId")})
	Music findMusicByCondition(@Param("artist") String artist,@Param("name") String name);

	/**
	 * 按照歌手查询
	 * @param artist
	 * @return
	 */
	@Select("SELECT a.musicId,a.name FROM music a LEFT JOIN relation_artlist_music b on a.musicId = b.musicId WHERE b.artistId = (" +
			"SELECT artistId FROM artist c WHERE c.name = #{artist} OR c.alia LIKE CONCAT(CONCAT('%', #{artist}), '%') LIMIT 1)")
	@Results({@Result(property = "id", column = "musicId")})
	List<Music> findListByArtist(@Param("artist") String artist);

	/**
	 * 按照歌曲名称查询
	 *
	 * @return musicId    的	List
	 */
	@Select("SELECT musicId FROM music WHERE NAME = #{name}")
	@Results({@Result(property = "id", column = "musicId")})
	List<String> findMusicBySongName(@Param("name") String name);

	/**
	 * 按照歌曲Id查询
	 *
	 * @return 歌手名称
	 */
	@Select("SELECT a.name FROM artist a LEFT JOIN relation_artlist_music b on a.artistId = b.artistId WHERE b.musicId = #{musicId}")
	String findMusicBySongId(@Param("musicId") String musicId);

}
