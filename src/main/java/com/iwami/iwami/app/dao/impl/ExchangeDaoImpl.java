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

	@Override
	public boolean addExchange(Exchange ex) {
		String sql = "insert into " + SqlConstants.TABLE_EXCHANGE +" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int line = getJdbcTemplate().update(sql,new Object[]{
				ex.getUserid(),
				ex.getPresentid(),
				ex.getPresentName(),
				ex.getPresentPrize(),
				ex.getPresentType(),
				ex.getCount(),
				ex.getPrize(),
				ex.getStatus(),
				ex.getCellPhone(),
				ex.getAlipayAcount(),
				ex.getBankAcount(),
				ex.getBankName(),
				ex.getAddress(),
				ex.getName(),
				ex.getExpress(),
				ex.getLastmodTime(),
				ex.getLastmodUserid(),
				ex.getIsdel()
		});
		if(line >0)
			return true;
		else
			return false;
	}

	@Override
	public boolean addExchangeList(List<Exchange> list) {
		String sql = "insert into " + SqlConstants.TABLE_EXCHANGE +" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int count = 0;
		for(Exchange ex: list){
			int line = getJdbcTemplate().update(sql,new Object[]{
					ex.getUserid(),
					ex.getPresentid(),
					ex.getPresentName(),
					ex.getPresentPrize(),
					ex.getPresentType(),
					ex.getCount(),
					ex.getPrize(),
					ex.getStatus(),
					ex.getCellPhone(),
					ex.getAlipayAcount(),
					ex.getBankAcount(),
					ex.getBankName(),
					ex.getAddress(),
					ex.getName(),
					ex.getExpress(),
					ex.getLastmodTime(),
					ex.getLastmodUserid(),
					ex.getIsdel()
			});
			if(line > 0)
				count ++;
		}
		if(count >0)
			return true;
		else
			return false;
	}

	@Override
	public boolean addPrize(long userid, int prize) {
		String sql = "update " + SqlConstants.TABLE_EXCHANGE + " set prize = prize + ? where userid = ? ";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{prize,userid});
		if(line >0)
			return true;
		else
			return false;
	}
}
