package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.StrategyRateBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class StrategyRateAjax {

	private Log logger = LogFactory.getLog(getClass());
	
	private StrategyRateBiz strategyRateBiz;
	
	@AjaxMethod(path = "srtategy/rate.ajax")
	public Map<Object,Object> strategyDetail(Map<String,String> params){
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		try{
			if(params.containsKey("id") && params.containsKey("uuid")){
				long strategyId = Long.parseLong(params.get("id"));
				String uuid = params.get("uuid");
				
				if(strategyRateBiz.getIdStatus(strategyId)){
					if(strategyRateBiz.getUuidStatus(uuid)){
						if(strategyRateBiz.getRepeatStatus()){
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
						}else
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_RATE_REPEAT);
					}else
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_RATE_UUID);
				}else
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_RATE_ID);
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

	public StrategyRateBiz getStrategyRateBiz() {
		return strategyRateBiz;
	}

	public void setStrategyRateBiz(StrategyRateBiz strategyRateBiz) {
		this.strategyRateBiz = strategyRateBiz;
	}
}