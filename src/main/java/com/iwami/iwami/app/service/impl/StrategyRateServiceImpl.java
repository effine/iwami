package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.RateInfoDao;
import com.iwami.iwami.app.dao.StrategyRateDao;
import com.iwami.iwami.app.model.RateInfo;
import com.iwami.iwami.app.service.StrategyRateService;

public class StrategyRateServiceImpl implements StrategyRateService {

	private RateInfoDao rateInfoDao;
	private StrategyRateDao strategyRateDao;

	public RateInfoDao getRateInfoDao() {
		return rateInfoDao;
	}

	public void setRateInfoDao(RateInfoDao rateInfoDao) {
		this.rateInfoDao = rateInfoDao;
	}

	public StrategyRateDao getStrategyRateDao() {
		return strategyRateDao;
	}

	public void setStrategyRateDao(StrategyRateDao strategyRateDao) {
		this.strategyRateDao = strategyRateDao;
	}

	@Override
	public boolean getStrategyIdStatus(long strategyId) {
		return rateInfoDao.getIdStatus(strategyId);
	}

	@Override
	public String pointPraise(RateInfo rateInfo) {
		return rateInfoDao.pointPraise(rateInfo);
	}

	@Override
	public boolean addSkim(long strategyId) {
		return strategyRateDao.addSkim(strategyId);
	}
}
