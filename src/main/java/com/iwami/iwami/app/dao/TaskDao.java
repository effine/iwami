package com.iwami.iwami.app.dao;

import com.iwami.iwami.app.model.Task;


public interface TaskDao {
	
	boolean getIdStatus(long taskid);
	
	Task getPrize(long taskid);
	
	

}
