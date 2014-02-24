package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.ExchangeDao;
import com.iwami.iwami.app.model.Exchange;

public class ExchangeDaoImpl extends JdbcDaoSupport implements ExchangeDao{

	@Override
	public List<Exchange> getExchangeList(long userid,int type) {
		String sql = "select * from " + SqlConstants.TABLE_EXCHANGE + " where userid = ?  and type <> ? group by express";
		List<Exchange> list = getJdbcTemplate().query(sql, new Object[]{userid,type},new RowMapper<Exchange>(){
			@Override
			public Exchange mapRow(ResultSet rs, int index)
					throws SQLException {
				Exchange e = new Exchange();
				e.setLastmodTime(rs.getLong("lastmod_time"));
				e.setPresentType(rs.getInt("type"));
				e.setExpress(rs.getString("express"));
				e.setPresentName(rs.getString("present_name"));
				e.setPresentPrize(rs.getInt("present_prize"));
				e.setCount(rs.getInt("count"));
				return e;
			}
		});
		return list;
	}
}
