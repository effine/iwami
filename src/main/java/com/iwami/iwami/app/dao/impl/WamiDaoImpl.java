package com.iwami.iwami.app.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.dao.WamiDao;

public class WamiDaoImpl extends JdbcDaoSupport implements WamiDao {

/*	@Override
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
	} */
}
