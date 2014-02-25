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
			boolean isContains = params.containsKey("userid") && params.containsKey("ids") && params.containsKey("count")
							  && params.containsKey("cellPhone") && params.containsKey("aliAccount") && params.containsKey("prize")
							  && params.containsKey("address") && params.containsKey("name") && params.containsKey("bankName")
							  && params.containsKey("bankAccount");
			if(isContains){
				long userid = Long.parseLong(params.get("userid"));
				//int[] ids = params.get("ids");     取出数组
				//int[] count = 
				long cellPhone = Long.parseLong(params.get("cellPhone"));
				String aliAccount = params.get("aliAccount");
				int prize = Integer.parseInt(params.get("prize"));
				String address = params.get("address");
				String name = params.get("name");
				String bankName = params.get("bankName");
				long bankAccount = Long.parseLong(params.get("bankAccount"));
				
				
				
				
				
				
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in gift/exchange ", t);
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
