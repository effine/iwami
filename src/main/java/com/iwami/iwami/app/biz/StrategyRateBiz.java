package com.iwami.iwami.app.biz;

public interface StrategyRateBiz {
	
	boolean getStrategyIdStatus(long strategyId);
	
	boolean getRepeatStatus(long strategyId,String uuid);
}
