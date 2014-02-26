package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.WamiTasksBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class WamiTasksAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	private WamiTasksBiz wamiTasksBiz;

	@AjaxMethod(path = "wami/tasks.ajax")
	public Map<Object, Object> statusUpload(Map<String,String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			if(params.containsKey("userid")){
				int userid = Integer.parseInt(params.get("userid"));
				result.putAll(wamiTasksBiz.getData(userid));
				result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_OK);
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in wami/tasks ", t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public WamiTasksBiz getWamiTasksBiz() {
		return wamiTasksBiz;
	}

	public void setWamiTasksBiz(WamiTasksBiz wamiTasksBiz) {
		this.wamiTasksBiz = wamiTasksBiz;
	}
}
