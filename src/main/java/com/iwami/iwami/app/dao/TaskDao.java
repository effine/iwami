package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.Task;


public interface TaskDao {
	
	boolean getIdStatus(long taskid);
	
	Task getPrize(long taskid);
	
	List<Task> getOrdinaryTask();

	List<Task> getTask();
}
