package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Comment;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper {
	/**
	 * 查询全部
	 * @return
	 */
	@Select("select * from comment")
	List<Comment> findAll();

	/**
	 * 单个查询
	 * @param songId
	 * @return
	 */
	@Select("select * from comment where songId = #{songId}")
	Comment findOne(@Param("songId") String songId);

	/**
	 * 查询数量
	 * @param songId
	 * @return
	 */
	@Select("select count(*) from comment where songId = #{songId}")
	int countByCommentId(@Param("songId") String songId);

	/**
	 * 单个插入
	 * @param comment
	 */
	@Insert("insert into comment(commentId,songId,nickname,linkedCount,content,time) value (#{commentId},#{songId},#{nickname},#{linkedCount},#{content},#{time})")
	void insert(Comment comment);

	/**
	 * 批量插入
	 * @param list
	 */
	@InsertProvider(type = CommentMapperProvider.class,method = "batchInsert")
	void batchInsert(@Param("list") List<Comment> list);

	/**
	 *  单个更新
	 * @param content
	 * @param commentId
	 */
	@Update("update comment set content = #{content} where commentId = #{commentId}")
	void updateOne(@Param("content")String content,@Param("commentId")String commentId);

	/**
	 * 批量更新
	 */
	@UpdateProvider(type = CommentMapperProvider.class,method = "batchUpdate")
	void batchUpdate(@Param("list") List<Comment> comments);

	/**
	 * 删除
	 * @param commentId
	 */
	@Delete("delete from comment where commentId = #{commentId}")
	void deleteByCommentId(@Param("commentId") String commentId);

	/**
	 * 批量删除
	 * @param comments
	 */
	@DeleteProvider(type = CommentMapperProvider.class,method = "batchDelete")
	void batchDelete(@Param("comments") List<Comment> comments);
	/**
	 *
	 *

	 @Insert("<script>"  +
	"INSERT INTO cms_portal_menu(name,service_type,index_code) VALUES" +
	"<foreach collection=\"list\" item=\"item1\" index=\"index\"  separator=\",\">" +
	"(#{item1.name},#{item1.serviceType},#{item1.indexCode})" +
	"</foreach>" +
	"</script>")
	 int batchInsert(@Param("list")List<Menu> menus);

	 <insert id="batchInsert" parameterType="java.util.List">
	 	insert into user(username, password) values
	 <foreach collection="list" item="item" index="index"
	 separator=",">
	 (#{item.username},
	 #{item.password} )
	 </foreach>
	 </insert>

	 <update id="batchUpdate" parameterType="java.util.List">
	 <foreach collection="list" item="item" index="index" open="" close="" separator=";">
	 update test <set> test=${item.test}+1 </set> where id = ${item.id}
	 </foreach>
	 </update>

	 <delete id="batchDeleteByIdList" parameterType="java.util.List">
	 delete from user
	 where id in
	 <foreach item="id" collection="list" open="(" separator="," close=")">
	 #{id}
	 </foreach>
	 </delete>
	 *
	 *
	 */
}
