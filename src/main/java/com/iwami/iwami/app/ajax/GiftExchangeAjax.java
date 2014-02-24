package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.GiftExchangeBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class GiftExchangeAjax {

	private Log logger = LogFactory.getLog(getClass());
	private GiftExchangeBiz giftExchangeBiz;
	
	@AjaxMethod(path = "gift/exchange.ajax")
	public Map<Object, Object> statusUpload(Map<String,String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			boolean isExist = params.containsKey("userid") && params.containsKey("taskid") && params.containsKey("type") && params.containsKey("time") && params.containsKey("channel");
			if(isExist){
				
				
				
				
				
				
				
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in GiftExchange ", t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public GiftExchangeBiz getGiftExchangeBiz() {
		return giftExchangeBiz;
	}

	public void setGiftExchangeBiz(GiftExchangeBiz giftExchangeBiz) {
		this.giftExchangeBiz = giftExchangeBiz;
	}
	
}
