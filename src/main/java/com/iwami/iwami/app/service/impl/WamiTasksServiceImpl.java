package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.TaskDao;
import com.iwami.iwami.app.model.Task;
import com.iwami.iwami.app.service.WamiTasksService;

public class WamiTasksServiceImpl implements WamiTasksService {

	private TaskDao taskDao;

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public List<Task> getTask(int type) {
		return taskDao.getTask(type);
	}
}
