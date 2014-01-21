package com.iwami.iwami.app.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.OnstartDao;
import com.iwami.iwami.app.model.Onstart;

public class OnstartDaoImpl extends JdbcDaoSupport implements OnstartDao{

	@Override
	public boolean logOnstart(Onstart onstart) {
		boolean result = false;
		if(onstart != null){
			int count = getJdbcTemplate().update("insert into " + SqlConstants.TABLE_ONSTART + "(`userid`,`cell_phone`,`uuid`,`type`,`add_time`,`lastmod_time`) values(?,?,?,?,?,now())", 
					new Object[]{onstart.getUserid(), onstart.getCellPhone(), onstart.getUuid(), onstart.getType(), onstart.getAddTime()});
			if(count > 0)
				result = true;
		}
		return result;
	}
}
