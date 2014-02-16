package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.impl.StrategyListBizImpl;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class StrategyListAjax {

	private Log logger = LogFactory.getLog(getClass());
	
	private StrategyListBizImpl strategyListBiz;
	
	@AjaxMethod(path = "strategyList.ajax")
	public Map<Object,Object> strategyList(){
		Map<Object,Object> result = new HashMap<Object,Object>();
		try{
			
			


				result.put(ErrorCodeConstants.STATUS_KEY,
						ErrorCodeConstants.STATUS_OK);
		}catch(Throwable t){
			if(logger.isErrorEnabled()){
				logger.error("Exception in  ",t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public StrategyListBizImpl getStrategyListBiz() {
		return strategyListBiz;
	}

	public void setStrategyListBiz(StrategyListBizImpl strategyListBiz) {
		this.strategyListBiz = strategyListBiz;
	}
	
}