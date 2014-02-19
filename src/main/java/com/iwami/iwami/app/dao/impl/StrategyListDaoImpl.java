package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.StrategyListDao;
import com.iwami.iwami.app.model.StrategyList;

public class StrategyListDaoImpl extends JdbcDaoSupport implements  StrategyListDao{
	
		@Override
		public List<StrategyList> getStragyList() {
			String sql ="select * from " + SqlConstants.TABLE_STRATEGY_LIST;
			List<StrategyList> list = getJdbcTemplate().query(sql,new RowMapper<StrategyList>(){
				@Override 
				public StrategyList mapRow(ResultSet rs, int index) throws SQLException {
					StrategyList sl = new StrategyList();
					sl.setId(rs.getLong("id"));
					sl.setName(rs.getString("name"));
					sl.setSubname(rs.getString("subname"));
					sl.setIntr(rs.getString("intr"));
					sl.setRank(rs.getInt("rank"));
					sl.setIconSmall(rs.getString("icon_small"));
					sl.setIconBig(rs.getString("icon_big"));
					sl.setLastmodTime(rs.getLong("lastmod_time"));
					sl.setLastmodUserid(rs.getInt("lastmod_userid"));
					sl.setIsdel(rs.getInt("isdel"));
					return sl;
				}
			});
				return list;
		} 
}
