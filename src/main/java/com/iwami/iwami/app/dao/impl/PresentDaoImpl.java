package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.PresentDao;
import com.iwami.iwami.app.model.Present;

public class PresentDaoImpl extends JdbcDaoSupport implements PresentDao{

	@Override
	public List<Present> getPresent(int start,int step) {
		String sql = "select * from " + SqlConstants.TABLE_PRESENT + " where type <> 4 and isdel = 0 order by rank limit ?,?";
		List<Present> list = getJdbcTemplate().query(sql, new Object[]{start,step},new RowMapper<Present>(){
			@Override
			public Present mapRow(ResultSet rs, int index) throws SQLException {
				Present p = new Present();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setPrize(rs.getInt("prize"));
				p.setRank(rs.getInt("rank"));
				p.setType(rs.getInt("type"));
				p.setIconSmall(rs.getString("icon_small"));
				p.setIconBig(rs.getString("icon_big"));
				p.setLastmodTime(rs.getLong("lastmod_time"));
				p.setLastmodUserid(rs.getInt("lastmod_userid"));
				p.setIsdel(rs.getInt("isdel"));
				return p;
			}
		});
		return list;
	}

	@Override
	public Present getIdStatus(long presentId) {
		String sql = "select * from " + SqlConstants.TABLE_PRESENT + " where id = ?";
		List<Present> list =  getJdbcTemplate().query(sql,new Object[]{presentId},new RowMapper<Present>(){
			@Override
			public Present mapRow(ResultSet rs, int index) throws SQLException {
				Present p = new Present();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setPrize(rs.getInt("prize"));
				p.setRank(rs.getInt("rank"));
				p.setType(rs.getInt("type"));
				p.setIconSmall(rs.getString("icon_small"));
				p.setIconBig(rs.getString("icon_big"));
				p.setLastmodTime(rs.getLong("lastmod_time"));
				p.setLastmodUserid(rs.getInt("lastmod_userid"));
				p.setIsdel(rs.getInt("isdel"));
				return p;
			}
		});
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}
}
