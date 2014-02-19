package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.StrategyListDao;
import com.iwami.iwami.app.model.StrategyList;
import com.iwami.iwami.app.service.StrategyListService;

public class StrategyListServiceImpl implements StrategyListService{
	
	private StrategyListDao strategyListDao;
	
	
	@Override
	public List<StrategyList> getStragyList() {
		return strategyListDao.getStragyList();
	}

	public StrategyListDao getStrategyListDao() {
		return strategyListDao;
	}

	public void setStrategyListDao(StrategyListDao strategyListDao) {
		this.strategyListDao = strategyListDao;
	}

}
