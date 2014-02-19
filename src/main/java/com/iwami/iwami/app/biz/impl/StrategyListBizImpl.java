package com.iwami.iwami.app.biz.impl;

import java.util.List;

import com.iwami.iwami.app.biz.StrategyListBiz;
import com.iwami.iwami.app.model.StrategyList;
import com.iwami.iwami.app.service.StrategyListService;

public class StrategyListBizImpl implements StrategyListBiz {
	
	private StrategyListService strategyListService;
	
	@Override
	public List<StrategyList> getStragyList() {
		return strategyListService.getStragyList();
	}

	public StrategyListService getStrategyListService() {
		return strategyListService;
	}

	public void setStrategyListService(StrategyListService strategyListService) {
		this.strategyListService = strategyListService;
	}


}
