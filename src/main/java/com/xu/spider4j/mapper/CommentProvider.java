package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Comment;
import org.apache.ibatis.jdbc.SQL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 评论SQL工厂类
 */
public class CommentProvider {


	public String findAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM("comment");
			}
		}.toString();
	}

	public String batchSelect(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		List<String> params = (List<String>) map.get("params");
		sb.append("SELECT * from comment where commentId in ");
		sb.append("(");
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i));
			if (i < params.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}


	public String batchInsert(Map<String, Object> map) {
		List<Comment> comments = (List<Comment>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO comment ");
		sb.append("(commentId,songId,nickname,linkedCount,content,time) ");
		sb.append("VALUES ");
		MessageFormat mf = new MessageFormat(
				"(#'{'list[{0}].id},#'{'list[{0}].songId},#'{'list[{0}].nickname},#'{'list[{0}].linkedCount},#'{'list[{0}].content},#'{'list[{0}].time})");
		for (int i = 0; i < comments.size(); i++) {
			sb.append(mf.format(new Object[]{i}));
			if (i < comments.size() - 1) {
				sb.append(",");
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

	public String batchUpdate(Map<String, Object> map) {
		List<Comment> comments = (List<Comment>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("update comment ");
		sb.append("set content=case ");
		MessageFormat mfCron = new MessageFormat("#'{'list[{0}].content'}'");
		for (int i = 0; i < comments.size(); i++) {
			sb.append("when (commentId=");
			sb.append(comments.get(i).getId());
			sb.append(")");
			sb.append(" then ");
			sb.append(mfCron.format(new Object[]{i}));
		}
		sb.append("end ");
		sb.append("where commentId in");
		sb.append("(");
		for (int i = 0; i < comments.size(); i++) {
			sb.append(comments.get(i).getId());
			if (i < comments.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		System.out.println(sb.toString());
		return sb.toString();
	}

	public String batchDelete(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		List<String> params = (List<String>) map.get("params");
		sb.append(" delete from comment where commentId in  ");
		sb.append("(");
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i));
			if (i < params.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 *
	 *
	 public String insertBlog(Map<String,Object> para){

	 Blog blog = (Blog)para.get("bean"); //

	 SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL

	 sql.INSERT_INTO("blog");

	 if(blog.getBlogId() != null){ //判断blogId属性是否有值
	 sql.VALUES("blogId", blog.getBlogId());
	 }

	 if(blog.getTitle() != null){//判断title属性是否有值
	 sql.VALUES("title", blog.getTitle());
	 }

	 if(blog.getAuthor() != null){//判断author属性是否有值
	 sql.VALUES("author", blog.getAuthor());
	 }

	 return sql.toString();
	 }
	 *
	 *
	 */


}
