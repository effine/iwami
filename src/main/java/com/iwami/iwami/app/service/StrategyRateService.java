package com.iwami.iwami.app.service;

import com.iwami.iwami.app.model.RateInfo;


public interface StrategyRateService {
	
	boolean getStrategyIdStatus(long strategyId);
	
	String pointPraise(RateInfo rateInfo);
	
	boolean addSkim(long strategyId);
	
}
