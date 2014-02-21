package com.iwami.iwami.app.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwami.iwami.app.biz.StrategyDetailBiz;
import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;
import com.iwami.iwami.app.service.StrategyDetailService;

public class StrategyDetailBizImpl implements StrategyDetailBiz {

	private StrategyDetailService strategyDetailService;

	@Override
	public Map<Object, Object> getData(long strategyId, int start, int step) {
		
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		if(this.getIdStatus(strategyId)){
			if(start == 0){
				strategyDetailService.updateStrategyRate(strategyId);

				List<StrategyRate> srList = strategyDetailService.getStrategyRate(strategyId);
				for(StrategyRate sr: srList){
					resultMap.put("skim", sr.getSkim());
					resultMap.put("rate", sr.getRate());
				}
			}
			
			List<StrategyInfo> siList = strategyDetailService.getStrategyInfo(strategyId, start, step); 
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>(); 
			for(StrategyInfo si: siList){
				Map<String,Object> siMap = new HashMap<String,Object>();
				siMap.put("id",si.getId());
				siMap.put("rak",si.getRank());
				siMap.put("title",si.getTitle());
				siMap.put("comment",si.getComment());
				mapList.add(siMap);
			}
			resultMap.put("strategy", mapList);
			resultMap.put("count", strategyDetailService.countStrategyInfo());
		}
		return resultMap;
	}
	
	@Override
	public boolean getIdStatus(long strategyId) {
		List<StrategyInfo> status = strategyDetailService.getStatus(strategyId);
		if(status != null && status.size() > 0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean getRateStatus(int start, long strategyId) {
		int rate = 0;
		List<StrategyRate> srList = strategyDetailService.getStrategyRate(strategyId);
		for(StrategyRate sr: srList){
			rate = sr.getRate();
		}
		if(start <= rate)
			return true;
		else
			return false;
	}

	public StrategyDetailService getStrategyDetailService() {
		return strategyDetailService;
	}

	public void setStrategyDetailService(
			StrategyDetailService strategyDetailService) {
		this.strategyDetailService = strategyDetailService;
	}
}
