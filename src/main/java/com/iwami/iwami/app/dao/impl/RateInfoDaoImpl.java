package com.iwami.iwami.app.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.RateInfoDao;
import com.iwami.iwami.app.model.RateInfo;

public class RateInfoDaoImpl extends JdbcDaoSupport implements RateInfoDao{

	@Override
	public boolean getIdStatus(long strategyId) {
		String sql = "select * from " + SqlConstants.TABLE_RATE_INFO + " a inner join " + SqlConstants.TABLE_STRATEGY_INFO + " b on a.strategy_id = b.strategy_id where a.strategy_id = ?";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{strategyId});
		if(line > 0 )
			return true;
		else
			return false;
	}

	@Override
	public boolean pointPraise(RateInfo ri) {
		String sql = "insert into " + SqlConstants.TABLE_RATE_INFO + " values(?,?,?,?)";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{
				ri.getStrategyId(),
				ri.getUuid(),
				ri.getLastmodTime(),
				ri.getIsdel()
		});
		
		if(line > 0)
			return true;
		else
			return false;
	}
}
