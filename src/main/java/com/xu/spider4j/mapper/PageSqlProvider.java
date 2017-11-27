package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.PageRequest;
import org.apache.ibatis.jdbc.SQL;

/**
 * 分页SQL工厂类
 *
 */
public  class PageSqlProvider {

	//用于拼接条件的分页列表查询，在子类中设置条件，sql为已拼接了条件的SQL对象。
	protected String findByPage(PageRequest request,String tableName){
		SQL sql =new SQL();
		sql.SELECT("*").FROM(tableName);
		if(request.getSorts()!=null&&request.getSorts().length!=0){
			for(int i=0;i<request.getSorts().length;i++){
				PageRequest.Sort sort = request.getSorts()[i];
				sql.ORDER_BY(sort.getField()+" "+sort.getType());
			}
		}
		String preSql = sql.toString();
		StringBuilder sb = new StringBuilder(preSql);
		sb.append(" limit #{page.start},#{page.size}");
		return sb.toString();
	}

}
