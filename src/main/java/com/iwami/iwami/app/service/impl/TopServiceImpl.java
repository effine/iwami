package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.TaskDao;
import com.iwami.iwami.app.model.Task;
import com.iwami.iwami.app.service.TopService;

public class TopServiceImpl implements TopService{
	
	private TaskDao taskDao;

	@Override
	public List<Task> getTask() {
		return taskDao.getTask();
	}

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

}
