package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Music;
import com.xu.spider4j.entity.PageRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 音乐SQL工厂类
 */
public class MusicSqlProvider extends PageSqlProvider{
	String tableName = "music";

	public String batchInsert(Map<String,Object> map){
		List<Music> comments = (List<Music>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT IGNORE INTO music ");
		sb.append("(musicId,name,album,score,commentThreadId) ");
		sb.append("VALUES ");
		MessageFormat mf = new MessageFormat(
				"(#'{'list[{0}].id},#'{'list[{0}].name},#'{'list[{0}].album},#'{'list[{0}].score},#'{'list[{0}].commentThreadId})");
		for (int i = 0; i < comments.size(); i++) {
			sb.append(mf.format(new Object[]{i}));
			if (i < comments.size() - 1) {
				sb.append(",");
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

	public String findByPage(Map<String,Object> map) {
		PageRequest request = (PageRequest)map.get("page");
		return super.findByPage(request,tableName);
	}
}
