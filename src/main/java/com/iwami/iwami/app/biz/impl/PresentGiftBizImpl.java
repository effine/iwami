package com.iwami.iwami.app.biz.impl;

import java.util.HashMap;
import java.util.Map;

import com.iwami.iwami.app.biz.PresentGiftBiz;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.service.PresentGiftService;

public class PresentGiftBizImpl implements PresentGiftBiz{
	
	private PresentGiftService presentGiftService;

	public PresentGiftService getPresentGiftService() {
		return presentGiftService;
	}

	public void setPresentGiftService(PresentGiftService presentGiftService) {
		this.presentGiftService = presentGiftService;
	}

	@Override
	public boolean getUseridStatus(long userid) {
		return presentGiftService.getUseridStatus(userid);
	}

	@Override
	public boolean getCellphoneStatus(long cellPhone) {
		return presentGiftService.getCellphoneStatus(cellPhone);
	}

	@Override
	public boolean getPrizeStatus(int prize,long userid) {
		int currPrize = presentGiftService.getCurrPrize(userid);
		if(prize <= currPrize)
			return true;
		else
			return false;
	}

	@Override
	public Map<String, Object> getData(long userid,long cellPhone,int prize) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> tempMap = new HashMap<String,Object>();
		tempMap.put("prize", presentGiftService.getCurrPrize(userid));
		map.put("data", tempMap);
		
		
		Exchange ex = new Exchange();
		ex.setUserid(userid);
		ex.setPresentid(presentGiftService.getUser(cellPhone).getUserid());
		ex.setPresentName("赠" + presentGiftService.getUser(cellPhone).getName());
		ex.setPresentPrize(-1);
		ex.setPresentType(5);
		ex.setCount(-1);
		ex.setPrize(prize);
		ex.setStatus(0);
		ex.setCellPhone(0);
		ex.setAlipayAcount(null);
		ex.setBankAcount(0);
		ex.setBankName(null);
		ex.setAddress(null);
		ex.setName(null);
		ex.setExpress(null);
		ex.setLastmodTime(System.currentTimeMillis());
		ex.setLastmodUserid((int)userid);
		ex.setIsdel(0);
		
		//TODO 需要对下面代码添加事务
		
		presentGiftService.addExchange(ex);
		int line = presentGiftService.subPrize(userid,prize);
		if(line == 0){
			ex.setStatus(1);
		}
		
		boolean b = presentGiftService.addPrize(presentGiftService.getUser(cellPhone).getUserid(), prize);
		if(b)
			ex.setStatus(3);
		
		//TODO 未完成后端逻辑
		/*
		 * （2）exchange加上米粒数
		 * （6）给被赠送方发送推送,必须在事务完成才可以进行
		 * 		1> 推送方案待定！！！！！！！！
		 * 		2> 推送话术待定!!!!!!!!!!!!！！
		 * 
		 * */
		
		//TODO 需要对以上代码添加事务
		return map;
	}
}
