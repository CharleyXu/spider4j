package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.RelationArtlistMusic;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class RelationArtlistMusicSqlProvider {

	public String batchInsert(Map<String,Object> map){
		List<RelationArtlistMusic> list = (List<RelationArtlistMusic>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT IGNORE INTO relation_artlist_music ");
		sb.append("(artistId,musicId) ");
		sb.append("VALUES ");
		MessageFormat mf = new MessageFormat(
				"(#'{'list[{0}].artistId},#'{'list[{0}].musicId})");
		for (int i = 0; i < list.size(); i++) {
			sb.append(mf.format(new Object[]{i}));
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

}
