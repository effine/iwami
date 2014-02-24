package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.WamiListBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class WamiListAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	private WamiListBiz wamiListBiz;

	@AjaxMethod(path = "wami/list.ajax")
	public Map<Object, Object> statusUpload(Map<String,String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			boolean isExist = params.containsKey("userid") && params.containsKey("taskid") && params.containsKey("type") && params.containsKey("time") && params.containsKey("channel");
			if(isExist){
				
				
				
				
				
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in wami", t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public WamiListBiz getWamiListBiz() {
		return wamiListBiz;
	}

	public void setWamiListBiz(WamiListBiz wamiListBiz) {
		this.wamiListBiz = wamiListBiz;
	}
}
