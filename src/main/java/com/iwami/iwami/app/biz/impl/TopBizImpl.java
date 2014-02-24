
package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.biz.TopBiz;
import com.iwami.iwami.app.model.Task;
import com.iwami.iwami.app.service.TopService;

public class TopBizImpl implements TopBiz {

	private TopService topService;

	@Override
	public Map<String,Object> getTask() {
		Map<String,Object> result = new HashMap<String,Object>();
		List<Task> list = topService.getTask();
		List<Map<String,Object>> beanList = new ArrayList<Map<String,Object>>();
		
		long currTime = System.currentTimeMillis();
		int available;
		long time = 0 ;
		
		if(list !=null  && list.size() > 0){ 	 
			for(Task task: list){
				if(currTime < task.getStartTime())
					list.remove(task);
				if(task.getEndTime() != 0 && currTime > task.getEndTime())
					list.remove(task);
				if(task.getMaxPrize()>0 && task.getMaxPrize() > task.getCurrentPrize()){
					available = 1;
				}else{
					available = 0;
				}
				if( task.getLastmodTime() > time){
					time = task.getLastmodTime();
				}
				
				Map<String,Object> beanMap =new HashMap<String,Object>();
				beanMap.put("id", task.getId());
				beanMap.put("name", task.getName());
				beanMap.put("rank", task.getRank());
				beanMap.put("size", task.getSize());
				beanMap.put("intr", task.getIntr());
				beanMap.put("prize", task.getPrize());
				beanMap.put("available", available);
				beanMap.put("background", task.getBackground());
				beanMap.put("register", task.getRegister());
				beanMap.put("time", task.getTime());
				beanMap.put("star", task.getStar());
				beanMap.put("reputation", task.getReputation());
				beanMap.put("iconSmall", task.getIconSmall());
				beanMap.put("iconBig", task.getIconBig());
				beanList.add(beanMap);
			}
		}
			result.put("data", beanList);
			result.put("time", time);
		return result;
	}
	
	public TopService getTopService() {
		return topService;
	}

	public void setTopService(TopService topService) {
		this.topService = topService;
	}

}
