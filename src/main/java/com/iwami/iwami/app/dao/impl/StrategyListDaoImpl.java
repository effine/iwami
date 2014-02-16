package com.iwami.iwami.app.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.dao.StrategyListDao;
import com.iwami.iwami.app.model.StrategyList;
import com.mysql.jdbc.ResultSet;

public class StrategyListDaoImpl extends JdbcDaoSupport implements  StrategyListDao{

		@Override
		public List<StrategyList> xxx() {
			String sql =" ";
			List<StrategyList> list = getJdbcTemplate().query(sql,new RowMapper<StrategyList>(){
				@Override 
				public StrategyList mapRow(ResultSet rs, int index) throws SQLException {
					
					StrategyList strategyList = new StrategyList();

					return strategyList;
				}
			});
				return list;
		} 
}
