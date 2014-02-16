package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.StrategyListDao;
import com.iwami.iwami.app.service.StrategyListService;

public class StrategyListServiceImpl implements StrategyListService{
	
	private StrategyListDao strategyListDao;

	public StrategyListDao getStrategyListDao() {
		return strategyListDao;
	}

	public void setStrategyListDao(StrategyListDao strategyListDao) {
		this.strategyListDao = strategyListDao;
	}
}
