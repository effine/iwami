package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.biz.PresentListBiz;
import com.iwami.iwami.app.model.Present;
import com.iwami.iwami.app.service.PresentListService;

public class PresentListBizImpl implements PresentListBiz {

	private PresentListService presentListService;

	public PresentListService getPresentListService() {
		return presentListService;
	}

	public void setPresentListService(PresentListService presentListService) {
		this.presentListService = presentListService;
	}

	@Override
	public Map<String, Object> getData(long userid, int start, int step) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prize", presentListService.getPrize(userid));
		
		List<Present> list = presentListService.getPresent(start, step);
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
		for(Present p: list){
			Map<String,Object>  beanMap = new HashMap<String,Object>();
			beanMap.put("id", p.getId());
			beanMap.put("name", p.getName());
			beanMap.put("prize", p.getPrize());
			beanMap.put("rank", p.getRank());
			beanMap.put("type", p.getType());
			beanMap.put("iconSmall", p.getIconSmall());
			beanMap.put("iconBig", p.getIconBig());
			tempList.add(beanMap);
		}
		map.put("data", tempList);
		return map;
	}
}
