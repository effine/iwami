package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
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
	public Map<String, Object> getData(long userid) {
		Map<Object,Object> tmap = new HashMap<Object,Object>();
		tmap.put(1, 1);
		
		Map<String,Object> map = new HashMap<String,Object>();
		int available = 0;
		List<Map<String,Object>> beanList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
		List<Task> tlist = wamiTasksService.getOrdinaryTask();
		for(Task t: tlist){
			Map<String,Object> beanMap = new HashMap<String,Object>();
			if(t.getMaxPrize() > 0){
				if(t.getMaxPrize() > t.getCurrentPrize())
					available = 0;
				else
					available = 1;
			}
			if(available == 0){
				beanMap.put("id", t.getId());
				beanMap.put("name", t.getName());
				beanMap.put("rank", t.getRank());
				beanMap.put("size", t.getSize());
				beanMap.put("intr", t.getIntr());
				beanMap.put("appintr", t.getAppintr());
				beanMap.put("prize", t.getPrize());
				beanMap.put("type", t.getType());
				beanMap.put("available", available);
				beanMap.put("background", t.getBackground());
				beanMap.put("register", t.getRegister());
				beanMap.put("time", t.getTime());
				beanMap.put("iconGraw", t.getIconGray());
				beanMap.put("iconSmall", t.getIconSmall());
				beanMap.put("iconBig", t.getIconBig());
				beanMap.put("lastmodtime", t.getLastmodTime());
				beanList.add(beanMap);
			}else{
				beanMap.put("id", t.getId());
				beanMap.put("name", t.getName());
				beanMap.put("rank", t.getRank());
				beanMap.put("size", t.getSize());
				beanMap.put("intr", t.getIntr());
				beanMap.put("appintr", t.getAppintr());
				beanMap.put("prize", t.getPrize());
				beanMap.put("type", t.getType());
				beanMap.put("available", available);
				beanMap.put("background", t.getBackground());
				beanMap.put("register", t.getRegister());
				beanMap.put("time", t.getTime());
				beanMap.put("iconGraw", t.getIconGray());
				beanMap.put("iconSmall", t.getIconSmall());
				beanMap.put("iconBig", t.getIconBig());
				beanMap.put("lastmodtime", t.getLastmodTime());
				orderList.add(beanMap);
			}
		}
		beanList.addAll(orderList);
		map.put("new", beanList);
		
		
		//TODO 不知道怎么取得下列值
		map.put("ongoing", null);
		map.put("done", null);
		
		map.put("days", null);
		map.put("count", null);
		
		return map;
	}
}
