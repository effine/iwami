package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.dao.WamiDao;
import com.iwami.iwami.app.model.Wami;

public class WamiDaoImpl extends JdbcDaoSupport implements WamiDao {

	@Override
	public List<Wami> xxx() {
		String sql =" ";
		List<Wami> list = getJdbcTemplate().query(sql,new RowMapper<Wami>(){
			@Override 
			public Wami mapRow(ResultSet rs, int index) throws SQLException {
				Wami wami = new Wami();

				return wami;
			}
		});
			return list;
	} 
}
