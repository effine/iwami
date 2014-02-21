package com.iwami.iwami.app.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.TaskDao;
import com.iwami.iwami.app.model.Task;

public class TaskDaoImpl extends JdbcDaoSupport implements TaskDao{

	@Override
	public List<Task> getIdStatus(long taskid) {
		
		String sql = "select id from " + SqlConstants.TABLE_TASK + "  where id = ?";
		
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{taskid});
		
		return null;
	}

}
