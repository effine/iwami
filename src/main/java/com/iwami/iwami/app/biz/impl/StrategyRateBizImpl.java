package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.StrategyRateBiz;
import com.iwami.iwami.app.model.RateInfo;
import com.iwami.iwami.app.service.RateInfoService;

public class StrategyRateBizImpl implements StrategyRateBiz {

	private RateInfoService rateInfoService;

	public RateInfoService getRateInfoService() {
		return rateInfoService;
	}

	public void setRateInfoService(RateInfoService rateInfoService) {
		this.rateInfoService = rateInfoService;
	}

	@Override
	public boolean getIdStatus(long strategyId) {
		return rateInfoService.getIdStatus(strategyId);
	}

	@Override
	public boolean getRepeatStatus(long strategyId,String uuid) {
		RateInfo ri = new RateInfo();
		ri.setStrategyId(strategyId);
		ri.setUuid(uuid);
		ri.setLastmodTime(System.currentTimeMillis());
		
		return rateInfoService.pointPraise(ri);
	}
}
