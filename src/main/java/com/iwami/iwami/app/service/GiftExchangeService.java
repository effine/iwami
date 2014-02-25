package com.iwami.iwami.app.service;

import java.util.List;

import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.Present;

public interface GiftExchangeService {
	
	boolean addExchangeList(List<Exchange> list);
	
	boolean subCurrPrize(long userid,int prize);
	
	boolean addPrize(long userid,int prize);
	
	boolean getUseridStatus(long userid);
	
	Present getPresent(long presentId);
	
	int getCurrPrize(long userid);
}
