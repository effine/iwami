package com.iwami.iwami.app.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.StrategyRateDao;

public class StrategyRateDaoImpl extends JdbcDaoSupport implements StrategyRateDao{

	@Override
	public boolean addSkim(long strategyId) {
		
		String sql = "update " + SqlConstants.TABLE_STRATEGY_RATE + " set skim = skim+1 where strategy_id = ?";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{strategyId});
		if(line >0)
			return true;
		else
			return false;
	}

}
