package com.iwami.iwami.app.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.WamiDao;
import com.iwami.iwami.app.model.Wami;

public class WamiDaoImpl extends JdbcDaoSupport implements WamiDao {

	@Override
	public boolean uploadStatus(Wami wami) {
		String sql ="insert into "+ SqlConstants.TABLE_WAMI +" values(?,?,?,?,?,?,?,?,?)";
		int line = getJdbcTemplate().update(sql, new Object[]{
				wami.getUserid(),
				wami.getTaskId(),
				wami.getType(),
				wami.getPrize(),
				wami.getChannel(),
				wami.getAddTime(),
				wami.getLastmodTime(),
				wami.getLastmodUserid(),
				wami.getIsdel()
		});
		
		if(line > 0)
			return true;
		else
			return false;
	} 
}
