package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.TaskDao;
import com.iwami.iwami.app.model.Task;

public class TaskDaoImpl extends JdbcDaoSupport implements TaskDao{

	@Override
	public boolean getIdStatus(long taskid) {
		String sql = "select * from " + SqlConstants.TABLE_TASK + " a inner join "+ SqlConstants.TABLE_WAMI +" b on a.id = b.task_id where b.id = ?";
		int line =  getJdbcTemplate().queryForInt(sql,new Object[]{taskid});
		if(line != 0 && line > 0)
			return true;
		else
			return false;
	}

	@Override
	public Task getPrize(long taskid) {
		String sql = "select * from " + SqlConstants.TABLE_TASK + " where id = ?";
		List<Task> list = getJdbcTemplate().query(sql, new Object[]{taskid},new RowMapper<Task>(){
			@Override
			public Task mapRow(ResultSet rs, int index) throws SQLException {
				Task task = new Task();
				task.setId(rs.getLong("id"));
				task.setPrize(rs.getInt("prize"));
				return task;
			}
		});
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
}
