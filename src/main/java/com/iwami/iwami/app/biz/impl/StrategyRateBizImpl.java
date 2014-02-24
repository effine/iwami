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
		ri.setLastmodTime(0);	//TODO 上次修改时间
		ri.setIsdel(0);	//TODO 是否删除
		
		return rateInfoService.pointPraise(ri);
	}
}
