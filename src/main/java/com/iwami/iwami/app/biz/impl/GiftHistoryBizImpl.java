package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.biz.GiftHistoryBiz;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.service.GiftHistoryService;

public class GiftHistoryBizImpl implements GiftHistoryBiz {

	private GiftHistoryService giftHistoryService;

	public GiftHistoryService getGiftHistoryService() {
		return giftHistoryService;
	}

	public void setGiftHistoryService(GiftHistoryService giftHistoryService) {
		this.giftHistoryService = giftHistoryService;
	}

	@Override
	public boolean getUseridStatus(long userid) {
		return giftHistoryService.getUseridStatus(userid);
	}

	@Override
	public Map<String, Object> getData(long userid) {
		int type = 5;  /* 表示赠送 ,除去type不为5的值*/
		Map<String, Object> map = new HashMap<String, Object>();
		User user = giftHistoryService.getExchangePrize(userid);
		if(user != null)
			map.put("exchangePrize",user.getCurrentPrize() + user.getExchangePrize());
		List<Exchange> list = giftHistoryService.getExchangeList(userid, type);
		map.put("count", list.size());
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
		for(Exchange e: list){
			Map<String,Object> beanMap = new HashMap<String,Object>();
			
			beanMap.put("time", e.getLastmodTime());
			beanMap.put("type", e.getPresentType());
			beanMap.put("express", e.getExpress());
			
			Map<String,Object> gMap = new HashMap<String,Object>();
			gMap.put("name", e.getPresentName());
			gMap.put("count", e.getCount());
			gMap.put("prize", e.getPresentPrize());
			beanMap.put("gifts", gMap);
			
			tempList.add(beanMap);
		}
		map.put("list", tempList);
		return map;
	}
}
