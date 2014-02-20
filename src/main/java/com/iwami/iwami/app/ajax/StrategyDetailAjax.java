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
	
	@AjaxMethod(path = "srtategyDetail.ajax")
	public Map<Object,Object> strategyDetail(Map<String,String> params){
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		try{
			if(params.containsKey("id") && params.containsKey("start") && params.containsKey("step")){
				int id = Integer.parseInt(params.get("id"));
				int start = Integer.parseInt(params.get("start"));
				int step = Integer.parseInt(params.get("step"));
				
				if(start < 0 ){
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_START);
				}
				
				if(step <= 0){
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_STEP);
				}
				
				Map<Object,Object> map = strategyDetailBiz.getData(id, start, step);
				if(start > Integer.parseInt(map.get("rate").toString())){
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STRATEGY_DETAIL_START1);
				}
				
			}else{
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
			}
				result.put(ErrorCodeConstants.STATUS_KEY,
						ErrorCodeConstants.STATUS_OK);
		}catch(Throwable t){
			if(logger.isErrorEnabled()){
				logger.error("Exception in strateDetail ",t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}
}