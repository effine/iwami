package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.StrategyDetailDao;
import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;

public class StrategyDetailDaoImpl extends JdbcDaoSupport implements StrategyDetailDao{
	
	@Override
	public List<StrategyInfo> getStatus(long strategyID){
		String sql = "select strategy_id from " + SqlConstants.TABLE_STRATEGY_INFO +" where strategy_id = ?";
		List<StrategyInfo> list = getJdbcTemplate().query(sql,new Object[]{strategyID} ,new RowMapper<StrategyInfo>(){
			@Override
			public StrategyInfo mapRow(ResultSet rs, int index)
					throws SQLException {
				StrategyInfo si = new StrategyInfo();
				si.setId(rs.getLong("id"));
				si.setSrtategyId(rs.getLong("strategy_id"));
				si.setRank(rs.getInt("rank"));
				si.setTitle(rs.getString("title"));
				si.setComment(rs.getString("content"));
				si.setUrl(rs.getString("url"));
				si.setLastmodTime(rs.getLong("lastmod_time"));
				si.setLastmodUserid(rs.getInt("lastmod_userid"));
				si.setIsdel(rs.getInt("isdel"));
				return si;
			}
		});
		return list;
	}

	@Override
	public List<StrategyInfo> getStrategyInfo(long strategyId, int start, int step){
		String sql = "select strategy_id from " + SqlConstants.TABLE_STRATEGY_INFO +" where strategy_id = ? and limit ?,?";
		List<StrategyInfo> siList = getJdbcTemplate().query(sql,new Object[]{strategyId,start,step}, new RowMapper<StrategyInfo>(){
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
		return siList;
	}
	
	@Override
	public int countStrategyInfo() {
			String cSql = "select count(*) from " + SqlConstants.TABLE_STRATEGY_INFO;
		return getJdbcTemplate().queryForInt(cSql);
	}
	
	
	@Override
	public List<StrategyRate> getStrategyRate(long strategyId){
		String  srSql = "select * from " + SqlConstants.TABLE_STRATEGY_RATE +" where strategy_id = ? ";
		List<StrategyRate> list = getJdbcTemplate().query(srSql,new Object[]{strategyId},new RowMapper<StrategyRate>(){
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
		return list;
	}
	
	@Override
	public int updateStrategyRate(long strategyId){
			String opSql = "update " + SqlConstants.TABLE_STRATEGY_RATE + " set skim = (skim+1) where strategy_id = ?" ;
		return getJdbcTemplate().update(opSql, new Object[]{strategyId});
	}
}
