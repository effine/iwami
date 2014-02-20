package com.iwami.iwami.app.biz.impl;

import java.util.Map;

import com.iwami.iwami.app.biz.StrategyDetailBiz;
import com.iwami.iwami.app.service.StrategyDetailService;

public class StrategyDetailBizImpl implements StrategyDetailBiz {

	private StrategyDetailService strategyDetailService;

	@Override
	public Map<Object, Object> getData(int id, int start, int step) {
		return strategyDetailService.getData(id, start, step);
	}

	public StrategyDetailService getStrategyDetailService() {
		return strategyDetailService;
	}

	public void setStrategyDetailService(
			StrategyDetailService strategyDetailService) {
		this.strategyDetailService = strategyDetailService;
	}

}
