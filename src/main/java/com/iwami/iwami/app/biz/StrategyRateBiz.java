package com.iwami.iwami.app.biz;

public interface StrategyRateBiz {
	
	boolean getIdStatus(long strategyId);
	
	boolean getUuidStatus(String uuid);
	
	boolean getRepeatStatus();
}
