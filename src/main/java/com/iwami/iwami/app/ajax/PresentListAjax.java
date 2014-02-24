package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.PresentListBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class PresentListAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	private PresentListBiz presentListBiz;
	
	@AjaxMethod(path = "present/list.ajax")
	public Map<Object, Object> statusUpload(Map<String,String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			boolean isExist = params.containsKey("userid") && params.containsKey("start") && params.containsKey("step");
			if(isExist){
				long userid = Long.parseLong(params.get("userid"));
				int start = Integer.parseInt(params.get("start"));
				int step = Integer.parseInt(params.get("step"));
				
				if(start >= 0){
					if(step > 0){
						result.putAll(presentListBiz.getData(userid,start,step));
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
					}else
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_PRESENT_LIST_STEP);
						result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_PRESENT_LIST_STEP));
				}else
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_PRESENT_LIST_START);
					result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_PRESENT_LIST_START));
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in present/list ", t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public PresentListBiz getPresentListBiz() {
		return presentListBiz;
	}


	public void setPresentListBiz(PresentListBiz presentListBiz) {
		this.presentListBiz = presentListBiz;
	}
}
