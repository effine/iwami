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
	public Map<Object, Object> getData(int id, int start, int step) {
		
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		Map<Object,Object> map = strategyDetailService.getData(id, start, step);
		
		if((Boolean) map.get("strategyId")){
			@SuppressWarnings("unchecked")
			List<StrategyRate> srList = (List<StrategyRate>) map.get("strategyRateList"); 
			for(StrategyRate sr: srList){
				if(start == 0){
					resultMap.put("skim", sr.getSkim());
					resultMap.put("rate", sr.getRate());
				}
			}
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			@SuppressWarnings("unchecked")
			List<StrategyInfo> siList = (List<StrategyInfo>) map.get("strategyInfoList"); 
			for(StrategyInfo si: siList){
				Map<String,Object> siMap = new HashMap<String,Object>();
				siMap.put("id",si.getId());
				siMap.put("rak",si.getRank());
				siMap.put("title",si.getTitle());
				siMap.put("comment",si.getComment());
				list.add(siMap);
			}
			
			resultMap.put("count", map.get("count"));
			resultMap.put("strategyId", map.get("strategyId"));
			resultMap.put("strategy", list);
			return resultMap;
		}else
			return map;
	}

	public StrategyDetailService getStrategyDetailService() {
		return strategyDetailService;
	}

	public void setStrategyDetailService(
			StrategyDetailService strategyDetailService) {
		this.strategyDetailService = strategyDetailService;
	}

}
