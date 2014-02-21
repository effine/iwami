package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.StrategyDetailDao;
import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;
import com.iwami.iwami.app.service.StrategyDetailService;

public class StrategyDetailServiceImpl implements StrategyDetailService {

	private StrategyDetailDao strategyDetailDao;

	@Override
	public List<StrategyInfo> getStatus(long strategyId) {
		return strategyDetailDao.getStatus(strategyId);
	}

	@Override
	public List<StrategyInfo> getStrategyInfo(long strategyId, int start, int step) {
		return strategyDetailDao.getStatus(strategyId);
	}

	@Override
	public int countStrategyInfo() {
		return strategyDetailDao.countStrategyInfo();
	}

	@Override
	public List<StrategyRate> getStrategyRate(long strategyId) {
		return strategyDetailDao.getStrategyRate(strategyId);
	}

	@Override
	public int updateStrategyRate(long strategyId) {
		return strategyDetailDao.updateStrategyRate(strategyId);
	}
	
	
	public StrategyDetailDao getStrategyDetailDao() {
		return strategyDetailDao;
	}

	public void setStrategyDetailDao(StrategyDetailDao strategyDetailDao) {
		this.strategyDetailDao = strategyDetailDao;
	}


}
