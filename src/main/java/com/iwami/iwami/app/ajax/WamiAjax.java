package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.WamiBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class WamiAjax {

	private Log logger = LogFactory.getLog(getClass());

	private WamiBiz wamiBiz;

	@AjaxMethod(path = "wami.ajax")
	public Map<Object, Object> wami(Map<String,String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			boolean isContains = params.containsKey("userid") && params.containsKey("taskid") && params.containsKey("type") && params.containsKey("time") && params.containsKey("channel");
			if(isContains){
				int userid = Integer.parseInt(params.get("userid"));
				long taskid = Long.parseLong(params.get("taskid"));
				int type = Integer.parseInt(params.get("type"));
				long time = 0;
				String channel = params.get("channel");
				
				if(wamiBiz.getIdStatus(taskid)){
					if(wamiBiz.getType(type)){
						if(wamiBiz.getUseridStatus(userid)){
							if(time != 0){
								time = Long.parseLong(params.get("type"));
								wamiBiz.statusUplod(userid,taskid,type,time,channel);
								result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
							}else
								result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_TIME);
								result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_TIME));
						}else
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_USERID);
							result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_USERID));
					}else
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_TYPE);
						result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_TYPE));
				}else
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_TASKID);
					result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_STATUSUPLOAD_TASKID));
				
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

	public WamiBiz getWamiBiz() {
		return wamiBiz;
	}

	public void setWamiBiz(WamiBiz wamiBiz) {
		this.wamiBiz = wamiBiz;
	}
}
