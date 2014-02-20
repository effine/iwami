package com.iwami.iwami.app.service.impl;

import java.util.Map;

import com.iwami.iwami.app.dao.StrategyDetailDao;
import com.iwami.iwami.app.service.StrategyDetailService;

public class StrategyDetailServiceImpl implements StrategyDetailService {

	private StrategyDetailDao strategyDetailDao;

	@Override
	public Map<Object, Object> getData(int id, int start, int step) {
		return strategyDetailDao.getData(id, start, step);
	}

	public StrategyDetailDao getStrategyDetailDao() {
		return strategyDetailDao;
	}

	public void setStrategyDetailDao(StrategyDetailDao strategyDetailDao) {
		this.strategyDetailDao = strategyDetailDao;
	}

}
