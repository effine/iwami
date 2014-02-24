package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.TopDao;
import com.iwami.iwami.app.model.Top;

public class TopDaoImpl extends JdbcDaoSupport implements TopDao {

	@Override
	public List<Top> getTop() {
		String sql ="select * from " + SqlConstants.TABLE_TASK + " where (type & 8) = 8 and isdel = 0 order by rank asc";
		List<Top> list = getJdbcTemplate().query(sql,new RowMapper<Top>(){
			
			int i = 0;
			
			@Override
			public Top mapRow(ResultSet rs, int index) throws SQLException {
				Top top = new Top();
				top.setId(rs.getLong("id"));
				top.setName(rs.getString("name"));
				top.setRank(++i);
				top.setSize(rs.getDouble("size"));
				top.setIntr(rs.getString("intr"));
				top.setPrize(rs.getInt("prize"));
				top.setType(rs.getInt("type"));
				top.setBackground(rs.getInt("background"));
				top.setRegister(rs.getInt("register"));
				top.setReputation(rs.getInt("reputation"));
				top.setStar(rs.getInt("star"));
				top.setStartTime(rs.getTimestamp("start_time").getTime());
				top.setEndTime(rs.getTimestamp("end_time").getTime());
				top.setCurrentPrize(rs.getInt("current_prize"));
				top.setMaxPrize(rs.getInt("max_prize"));
				top.setTime(rs.getInt("time"));
				top.setIconGray(rs.getString("icon_gray"));
				top.setIconSmall(rs.getString("icon_small"));
				top.setIconBig(rs.getString("icon_big"));
				top.setLastmodTime(rs.getTimestamp("lastmod_time").getTime());
				top.setLastmodUserid(rs.getInt("lastmod_userid"));
				top.setIsdel(rs.getInt("isdel"));
				return top;
			}
		});
			return list;
	} 
}
