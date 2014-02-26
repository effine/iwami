package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.StrategyRateBiz;
import com.iwami.iwami.app.model.RateInfo;
import com.iwami.iwami.app.service.StrategyRateService;

public class StrategyRateBizImpl implements StrategyRateBiz {

	private StrategyRateService strategyRateService;

	public StrategyRateService getStrategyRateService() {
		return strategyRateService;
	}

	public void setStrategyRateService(StrategyRateService strategyRateService) {
		this.strategyRateService = strategyRateService;
	}

	@Override
	public boolean getStrategyIdStatus(long strategyId) {
		return strategyRateService.getStrategyIdStatus(strategyId);
	}

	@Override
	public boolean getRepeatStatus(long strategyId, String uuid) {
		RateInfo ri = new RateInfo();
		ri.setStrategyId(strategyId);
		ri.setUuid(uuid);
		ri.setLastmodTime(System.currentTimeMillis());
		ri.setIsdel(0);
		
		// TODO 插入rate_info表，如捕获到unique异常返回100073;返回正常则更新strategy_rate表skim+1，需要添加事务
		String s = strategyRateService.pointPraise(ri);
		if(s != null && "unique".equalsIgnoreCase(s))
			return strategyRateService.addSkim(strategyId);
		else
			return false;
	}
}
