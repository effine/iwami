package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.TipsDao;
import com.iwami.iwami.app.model.Tips;

public class TipsDaoImpl extends JdbcDaoSupport implements TipsDao{

	@Override
	public String getContent(int type) {
		String sql = "select * from "+ SqlConstants.TABLE_TIPS +" where isdel = 0  and type = ? order by lastmod_time desc  limit 1";
		List<Tips> content = getJdbcTemplate().query(sql,new Object[]{type}, new RowMapper<Tips>(){
			@Override
			public Tips mapRow(ResultSet rs, int index) throws SQLException {
				Tips tips  =  new Tips();
				tips.setId(rs.getLong("id"));
				tips.setType(rs.getInt("type"));
				tips.setContent(rs.getString("content"));
				tips.setLastmodTime(rs.getLong("lastmodTime"));
				tips.setLastmodUserid(rs.getLong("lastmodUserid"));
				return tips;
			}
		});
		
		if(content != null && content.size() > 0)
			return content.get(0).getContent();
		else
			return null;
	}
}
