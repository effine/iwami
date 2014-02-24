package com.iwami.iwami.app.service;

import java.util.List;

import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.User;

public interface GiftHistoryService {
	
	User getExchangePrize(long userid);
	
	List<Exchange> getExchangeList(long userid,int type);
	
	boolean getUseridStatus(long userid);

}
 