package com.iwami.iwami.app.biz;

import java.util.Map;

public interface StrategyDetailBiz {
	
	Map<Object,Object> getData(long strategyId,int start,int step);

	boolean getIdStatus(long strategyId);
	
	boolean getRateStatus(int start,long strategyId);
	
}
