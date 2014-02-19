package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.StrategyImagesDao;
import com.iwami.iwami.app.model.StrategyImages;

public class StrategyImagesDaoImpl extends JdbcDaoSupport implements StrategyImagesDao{

	@Override
	public List<StrategyImages> getStratrgyImages() {
		String sql ="select * from " + SqlConstants.TABLE_STRATEGY_IMAGES;
		List<StrategyImages> list = getJdbcTemplate().query(sql,new RowMapper<StrategyImages>(){
			@Override 
			public StrategyImages mapRow(ResultSet rs, int index) throws SQLException {
				StrategyImages si = new StrategyImages();
				si.setId(rs.getLong("id"));
				si.setRank(rs.getInt("rank"));
				si.setIconUrl(rs.getString("icon_url"));
				si.setLastmodTime(rs.getLong("lastmod_time"));
				si.setLastmodUserid(rs.getInt("lastmod_userid"));
				si.setIsdel(rs.getInt("isdel"));
				return si;
			}
		});
			return list;
	}
}
