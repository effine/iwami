package com.iwami.iwami.app.biz;

public interface StrategyRateBiz {
	
	boolean getIdStatus(long strategyId);
	
	boolean getRepeatStatus(long strategyId,String uuid);
}
