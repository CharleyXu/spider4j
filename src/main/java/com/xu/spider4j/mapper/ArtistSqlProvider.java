package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Artist;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 歌手SQL工厂类
 */
public class ArtistSqlProvider {

	public String batchInsert(Map<String,Object> map){
		StringBuilder sb = new StringBuilder();
		List<Artist> list = (List<Artist>)map.get("list");
		sb.append("INSERT IGNORE INTO artist ");
		sb.append("(artistId,name,alia) ");
		sb.append("VALUES ");
		MessageFormat mf = new MessageFormat(
				"(#'{'list[{0}].id},#'{'list[{0}].name},#'{'list[{0}].alia})");
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
