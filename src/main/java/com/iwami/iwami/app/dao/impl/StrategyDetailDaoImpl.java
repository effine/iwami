package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.StrategyDetailDao;
import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;

public class StrategyDetailDaoImpl extends JdbcDaoSupport implements StrategyDetailDao{

	@Override
	public Map<Object, Object> getData(int id, int start, int step) {
		
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		
		String sql = "select strategy_id from " + SqlConstants.TABLE_STRATEGY_INFO +" where strategy_id = ?";
		List<StrategyInfo> lt = getJdbcTemplate().query(sql,new Object[]{id} ,new RowMapper<StrategyInfo>(){

			@Override
			public StrategyInfo mapRow(ResultSet rs, int index)
					throws SQLException {
				StrategyInfo si = new StrategyInfo();
				si.setSrtategyId(rs.getLong("strategy_id"));
				return si;
			}
		});
		
		if(lt != null && lt.size()>0){
			resultMap.put("strategyId", true);
			if(start == 0){
				String opSql = "update " + SqlConstants.TABLE_STRATEGY_RATE + " set skim = (skim+1) where strategy_id = ?" ;
				getJdbcTemplate().update(opSql, new Object[]{id});
			}
			String  srSql = "select * from " + SqlConstants.TABLE_STRATEGY_RATE;
			List<StrategyRate> srList = getJdbcTemplate().query(srSql,new RowMapper<StrategyRate>(){
				@Override
				public StrategyRate mapRow(ResultSet rs, int index) throws SQLException {
					StrategyRate sr = new StrategyRate();
					sr.setStrategyId(rs.getLong("strategy_id"));
					sr.setSkim(rs.getInt("skim"));
					sr.setRate(rs.getInt("rate"));
					sr.setIsdel(rs.getInt("isdel"));
					return sr;
				}
			});
			resultMap.put("strategyRateList", srList);
			
			String cSql = "select count(*) from " + SqlConstants.TABLE_STRATEGY_INFO;
			resultMap.put("count", getJdbcTemplate().queryForInt(cSql));
			
			String opSql = sql + " and limit ?,? ";
			List<StrategyInfo> siList = getJdbcTemplate().query(opSql,new Object[]{start,step}, new RowMapper<StrategyInfo>(){
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
			resultMap.put("strategyInfoList", siList);
		}else
			resultMap.put("strategyId", false) ;
		return resultMap;
	}
}
