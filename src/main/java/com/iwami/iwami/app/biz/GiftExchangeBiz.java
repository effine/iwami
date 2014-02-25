package com.iwami.iwami.app.biz;

public interface GiftExchangeBiz {
	
	boolean getUseridStatus(long userid);
	
	boolean getGiftidStatus(int[] ids);
	
	boolean getCountStatus(int[] count);
	
	boolean getPrizeStatus(int[] ids,int[] count,long userid);
	
	boolean updateData(long userid,int[] ids,int[] count,long cellPhone,String aliAccount,int prize,String address,String name,String bankName,long bankAccount);

}
