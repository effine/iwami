
package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.biz.TopBiz;
import com.iwami.iwami.app.model.Top;
import com.iwami.iwami.app.service.TopService;

public class TopBizImpl implements TopBiz {

	private TopService topService;

	@Override
	public Map<Object,Object> getTop() {
		Map<Object,Object> result = new HashMap<Object,Object>();
		List<Top> list = topService.getTop();
		List<Map<String,Object>> beanList = new ArrayList<Map<String,Object>>();
		
		long currTime = System.currentTimeMillis();
		int available;
		long time = 0 ;
		
		if(list !=null  && list.size() > 0){ 	 
			for(Top top: list){
				if(currTime < top.getStartTime())
					list.remove(top);
				if(top.getEndTime() != 0 && currTime > top.getEndTime())
					list.remove(top);
				if(top.getMaxPrize()>0 && top.getMaxPrize() > top.getCurrentPrize()){
					available = 1;
				}else{
					available = 0;
				}
				if( top.getLastmodTime() > time){
					time = top.getLastmodTime();
				}
				
				Map<String,Object> beanMap =new HashMap<String,Object>();
				beanMap.put("id", top.getId());
				beanMap.put("name", top.getName());
				beanMap.put("rank", top.getRank());
				beanMap.put("size", top.getSize());
				beanMap.put("intr", top.getIntr());
				beanMap.put("prize", top.getPrize());
				beanMap.put("available", available);
				beanMap.put("background", top.getBackground());
				beanMap.put("register", top.getRegister());
				beanMap.put("time", top.getTime());
				beanMap.put("star", top.getStar());
				beanMap.put("reputation", top.getReputation());
				beanMap.put("iconSmall", top.getIconSmall());
				beanMap.put("iconBig", top.getIconBig());
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
