package com.iwami.iwami.app.biz.impl;

import java.util.HashMap;
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
		Map<String,Object> map = new HashMap<String,Object>();
		long currTime = System.currentTimeMillis();
		int available = 0;
		List<Task> tlist = wamiTasksService.getOrdinaryTask();
		for(Task t: tlist){
			if(currTime < t.getStartTime())
				tlist.remove(t);
			if(t.getEndTime() != 0 && currTime > t.getEndTime())
				tlist.remove(t);
			if(t.getMaxPrize() > 0){
				if(t.getMaxPrize() > t.getCurrentPrize())
					available = 0;
				else
					available = 1;
			}
		}
		
		map.put("days", null);
		map.put("count", null);
		
		return map;
	}
}
