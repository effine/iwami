package com.iwami.iwami.app.service;

import java.util.List;

import com.iwami.iwami.app.model.Task;

public interface WamiTasksService {
	
	List<Task> getTask(int type);

}
