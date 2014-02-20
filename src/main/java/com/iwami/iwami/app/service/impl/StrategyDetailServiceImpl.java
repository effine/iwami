package com.iwami.iwami.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.dao.StrategyDetailDao;
import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;
import com.iwami.iwami.app.service.StrategyDetailService;

public class StrategyDetailServiceImpl implements StrategyDetailService {

	private StrategyDetailDao strategyDetailDao;

	@Override
	public Map<Object, Object> getData(int id, int start, int step) {
		
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		
		List<StrategyInfo> list = strategyDetailDao.getStrategyInfo(id);
		
		if(list != null && list.size() > 0){
			resultMap.put("strategyId", true);
			if(start == 0){
				List<StrategyRate> srList = strategyDetailDao.getStrategyRate();
				strategyDetailDao.updateStrategyRate(id);
				resultMap.put("skim", null);
				
			}
		}else
			resultMap.put("strategyId", false) ;
		return resultMap;
	}

	public StrategyDetailDao getStrategyDetailDao() {
		return strategyDetailDao;
	}

	public void setStrategyDetailDao(StrategyDetailDao strategyDetailDao) {
		this.strategyDetailDao = strategyDetailDao;
	}

}
