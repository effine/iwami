package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.iwami.iwami.app.biz.GiftExchangeBiz;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.Present;
import com.iwami.iwami.app.service.GiftExchangeService;

public class GiftExchangeBizImpl implements GiftExchangeBiz {

	private GiftExchangeService giftExchangeService;

	public GiftExchangeService getGiftExchangeService() {
		return giftExchangeService;
	}

	public void setGiftExchangeService(GiftExchangeService giftExchangeService) {
		this.giftExchangeService = giftExchangeService;
	}
	
	@Override
	public boolean updateData(long userid, int[] ids, int[] count,
			long cellPhone, String aliAccount, int prize, String address,
			String name, String bankName, long bankAccount) {
		
		/* TODO 以下代码添加事务 */

		// 批量插入exchange表
		List<Exchange> list = new ArrayList<Exchange>();
		for(int i=0;i<ids.length;i++){
			Exchange ex = new Exchange();
			ex.setUserid(userid); // ---
			ex.setPresentid(ids[i]); // ---
			ex.setPresentName(giftExchangeService.getPresent(ids[i]).getName());
			ex.setPresentPrize(-1);
			ex.setPresentType(giftExchangeService.getPresent(ids[i]).getPrize());
			ex.setCount(count[i]);  
			ex.setPrize(giftExchangeService.getPresent(ids[i]).getPrize()*count[i]);	 
			ex.setStatus(0);
			ex.setCellPhone(cellPhone);   
			ex.setAlipayAcount(aliAccount);  
			ex.setBankAcount(bankAccount); 
			ex.setBankName(bankName);	 
			ex.setAddress(address);		 
			ex.setName(name);		 
			ex.setExpress(null);
			ex.setLastmodTime(System.currentTimeMillis());
			ex.setLastmodUserid(0);
			ex.setIsdel(0);
			list.add(ex);
		}
		giftExchangeService.addExchangeList(list);
		
		giftExchangeService.subCurrPrize(userid, prize);
		giftExchangeService.addPrize(userid, prize);
		
		//以上代码添加事务
		
		return false;
	}

	@Override
	public boolean getUseridStatus(long userid) {
		return giftExchangeService.getUseridStatus(userid);
	}

	@Override
	public boolean getGiftidStatus(int[] ids) {
		boolean flag = false;
		for(long l: ids){
			Present p = giftExchangeService.getPresent(l);
			if(p != null)
				flag = true;
			else
				flag = false;
		}
		return flag;
	}

	@Override
	public boolean getCountStatus(int[] count) {
		boolean flag = false;
		for(int i:count){
			if(i>0)
				flag = true;
			else
				flag = false;
		}
		return flag;
	}

	@Override
	public boolean getPrizeStatus(int[] ids, int[] count, long userid) {
		int currPrize =  giftExchangeService.getCurrPrize(userid);
		int costPrize = 0;
		
		for(int i=0;i<ids.length;i++){
			long presentId = ids[i];
			int presentPrize = giftExchangeService.getPresent(presentId).getPrize();
			long num = count[i];
			costPrize += num*presentPrize;
		}
		
		if(currPrize < costPrize)
			return false;
		else
			return true;
	}
}
