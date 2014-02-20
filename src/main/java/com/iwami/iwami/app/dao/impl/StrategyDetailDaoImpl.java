package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.dao.StrategyDetailDao;
import com.iwami.iwami.app.model.StrategyInfo;

public class StrategyDetailDaoImpl extends JdbcDaoSupport implements StrategyDetailDao{

	@Override
	public Map<Object, Object> getData(int id, int start, int step) {
		
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		String sql = "";
		List<StrategyInfo> list = getJdbcTemplate().query(sql, new RowMapper<StrategyInfo>(){

			@Override
			public StrategyInfo mapRow(ResultSet rs, int index) throws SQLException {
				StrategyInfo si = new StrategyInfo();
					si.setId(rs.getLong("id"));
					si.setSrtategyId(rs.getLong("strategy_id"));
					si.setRank(rs.getInt("rank"));
					si.setTitle(rs.getString("title"));
					si.setComment(rs.getString("content"));
					si.setLastmodTime(rs.getLong("lastmod_time"));
					si.setLastmodUserid(rs.getInt("lastmod_userid"));
					si.setIsdel(rs.getInt("isdel"));
				return si;
			}
			
			
		});
		
		resultMap.put("list", list);
		
		return resultMap;
	}
}
