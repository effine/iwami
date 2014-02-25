package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.Exchange;

public interface ExchangeDao {
	
	List<Exchange> getExchangeList(long userid,int type);
	
	boolean addExchange(Exchange ex);
	
	boolean addExchangeList(List<Exchange> list);
	
	boolean addPrize(long userid,int prize);

}
