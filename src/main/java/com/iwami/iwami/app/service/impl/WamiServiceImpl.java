package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.TaskDao;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.dao.WamiDao;
import com.iwami.iwami.app.model.Task;
import com.iwami.iwami.app.model.Wami;
import com.iwami.iwami.app.service.WamiService;

public class WamiServiceImpl implements WamiService {

	private WamiDao wamiDao;
	private TaskDao taskDao;
	private UserDao userDao;

	
	public WamiDao getWamiDao() {
		return wamiDao;
	}

	public void setWamiDao(WamiDao wamiDao) {
		this.wamiDao = wamiDao;
	}

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Task getPrize(long taskid) {
		return taskDao.getPrize(taskid);
	}


	@Override
	public boolean getIdStatus(long taskid) {
		return taskDao.getIdStatus(taskid);
	}

	@Override
	public boolean getUseridStatus(int userid) {
		return userDao.getUseridStatus(userid);
	}

	@Override
	public boolean statusUplod(Wami wami) {
		if(wami.getType() == 4){
			/* type=4需要更新task的current_prize和user的current_prize,需要使用事务来操作 */
			//TODO 事务怎么控制？
			return false;
		}
		else
			return wamiDao.uploadStatus(wami);
	}
}
