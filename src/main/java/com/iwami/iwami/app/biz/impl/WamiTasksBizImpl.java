package com.iwami.iwami.app.biz.impl;

import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.biz.WamiTasksBiz;
import com.iwami.iwami.app.model.Task;
import com.iwami.iwami.app.service.WamiTasksService;

public class WamiTasksBizImpl implements WamiTasksBiz {

	private WamiTasksService wamiTasksService;

	public WamiTasksService getWamiTasksService() {
		return wamiTasksService;
	}

	public void setWamiTasksService(WamiTasksService wamiTasksService) {
		this.wamiTasksService = wamiTasksService;
	}

	@Override
	public Map<String, Object> getData() {
		
		int type = 1 ;	/*  普通任务： 二进制第一位  */
		List<Task> tlist = wamiTasksService.getTask(type);
		
		
		
		
		
		return null;
	}
}
