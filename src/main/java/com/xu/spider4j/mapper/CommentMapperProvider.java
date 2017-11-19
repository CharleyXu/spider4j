package com.xu.spider4j.mapper;

import com.google.common.base.Strings;
import com.xu.spider4j.entity.Comment;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * SQL工厂类
 */
public class CommentMapperProvider {


  public String findAll() {
    return new SQL() {
      {
        SELECT("*");
        FROM("comment");
      }
    }.toString();
  }




  public String batchInsert(Map<String, Object> map) {
    List<Comment> comments = (List<Comment>) map.get("list");
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO comment ");
    sb.append("(commentId,songId,nickname,linkedCount,content,time) ");
    sb.append("VALUES ");
    MessageFormat mf = new MessageFormat(
        "(#'{'list[{0}].commentId},#'{'list[{0}].songId},#'{'list[{0}].nickname},#'{'list[{0}].linkedCount},#'{'list[{0}].content},#'{'list[{0}].time})");
    for (int i = 0; i < comments.size(); i++) {
      sb.append(mf.format(new Object[]{i}));
      if (i < comments.size() - 1) {
        sb.append(",");
      }
    }
    System.out.println(sb.toString());
    return sb.toString();
  }

  public String batchUpdate(Map<String, Object> map) {
    List<Comment> comments = (List<Comment>) map.get("list");
    SQL sql = new SQL();
    if (null != comments) {
      sql.UPDATE("comment");

    }
    StringBuilder sb = new StringBuilder();
    sb.append("update comment ");
    sb.append("set content=case ");
    MessageFormat mfCron = new MessageFormat("#'{'list[{0}].content'}'");
    for (int i = 0; i < comments.size(); i++) {
      sb.append("when (commentId=");
      sb.append(comments.get(i).getCommentId());
      sb.append(")");
      sb.append(" then ");
      sb.append(mfCron.format(new Object[]{i}));
    }
    sb.append("end ");
    sb.append("where commentId in");
    sb.append("(");
    for (int i = 0; i < comments.size(); i++) {
      sb.append(comments.get(i).getCommentId());
      if (i < comments.size() - 1) {
        sb.append(",");
      }
    }
    sb.append(")");
    System.out.println(sb.toString());
    return sb.toString();
  }

  public String batchDelete(Map<String, Object> map) {
    return null;
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
