package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.StrategyDetailBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class StrategyDetailAjax {

	private Log logger = LogFactory.getLog(getClass());
	
	private StrategyDetailBiz strategyDetailBiz;
	
	@AjaxMethod(path = "srtategy/detail.ajax")
	public Map<Object,Object> strategyDetail(Map<String,String> params){
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		try{
			if(params.containsKey("id") && params.containsKey("start") && params.containsKey("step")){
				int id = Integer.parseInt(params.get("id"));
				int start = Integer.parseInt(params.get("start"));
				int step = Integer.parseInt(params.get("step"));
				if(start >= 0 ){
					if(step > 0){
						Map<Object,Object> map = strategyDetailBiz.getData(id, start, step);
						
						if((Boolean) map.get("strategyId")){
							if(start <= Integer.parseInt(map.get("rate").toString())){
								result.putAll(map);
								result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
							}else
								result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_START1);
						}else
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_ID);
					}else
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_STEP);
				}else
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_START);
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		}catch(Throwable t){
			if(logger.isErrorEnabled()){
				logger.error("Exception in strateDetail ",t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public StrategyDetailBiz getStrategyDetailBiz() {
		return strategyDetailBiz;
	}

	public void setStrategyDetailBiz(StrategyDetailBiz strategyDetailBiz) {
		this.strategyDetailBiz = strategyDetailBiz;
	}
}