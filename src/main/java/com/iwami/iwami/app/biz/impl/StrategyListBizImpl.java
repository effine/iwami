package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.StrategyListBiz;
import com.iwami.iwami.app.service.StrategyListService;

public class StrategyListBizImpl implements StrategyListBiz {
	
	private StrategyListService strategyListService;
	
	
	
	

	public StrategyListService getStrategyListService() {
		return strategyListService;
	}

	public void setStrategyListService(StrategyListService strategyListService) {
		this.strategyListService = strategyListService;
	}
}
