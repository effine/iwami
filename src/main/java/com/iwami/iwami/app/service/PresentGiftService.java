package com.iwami.iwami.app.service;

import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.User;

public interface PresentGiftService {
	
	boolean getUseridStatus(long userid);
	
	boolean getCellphoneStatus(long cellPhone);
	
	int getCurrPrize(long userid);
	
	User getUser(long cellPhone);
	
	boolean addExchange(Exchange ex);
	
	int subPrize(long userid,int prize);
	
	boolean addPrize(long userid,int prize);

}
