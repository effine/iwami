package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.Task;

public interface TaskDao {
	
	List<Task> getIdStatus(long taskid);

}
