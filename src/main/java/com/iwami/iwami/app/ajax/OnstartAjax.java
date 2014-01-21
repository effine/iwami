package com.iwami.iwami.app.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.OnstartBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;
import com.iwami.iwami.app.model.Onstart;


@AjaxClass
public class OnstartAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private OnstartBiz onstartBiz;

	@AjaxMethod(path = "onstart.ajax")
	public Map<Object, Object> onstart(Map<String, String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		try{
			long userid = -1;
			long cellPhone = -1;
			String uuid = StringUtils.EMPTY;
			int type = -1;
			long time = 0;
			
			if(params.containsKey("userid")){
				userid = NumberUtils.toLong(params.get("userid"));
				if(params.containsKey("cellPhone")){
					cellPhone = NumberUtils.toLong(params.get("cellPhone"));
					if(params.containsKey("uuid")){
						uuid = params.get("uuid");
					}
					
					if(StringUtils.isNotBlank(uuid)){
						System.out.println("0");
						if(params.containsKey("type")){
							type = NumberUtils.toInt(params.get("type"), -1);
						}
						System.out.println(type);
						if(type >= 0){
							System.out.println("1");
							if(params.containsKey("time")){
								time = NumberUtils.toLong(params.get("time"), 0);
							}
							System.out.println(time);
							
							if(time > 0){
								Onstart onstart = new Onstart();
								onstart.setUserid(userid);
								onstart.setCellPhone(cellPhone);
								onstart.setUuid(uuid);
								onstart.setType(type);
								onstart.setAddTime(new Date(time));
								
								if(onstartBiz.logOnstart(onstart)){
									result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_OK);
									result.remove(ErrorCodeConstants.MSG_KEY);
								} else{
									result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_ERROR);
								}
							} else{
								result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_PARAM_ONSTART_TIME);
								result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_PARAM_ONSTART_TIME));
							}
						} else{
							System.out.println("2");
							result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_PARAM_ONSTART_TYPE);
							result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_PARAM_ONSTART_TYPE));
						}
					} else{
						result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_PARAM_ONSTART_UUID);
						result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_PARAM_ONSTART_UUID));
					}
				} else{
					result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_PARAM_ONSTART_CELLPHONE);
					result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_PARAM_ONSTART_CELLPHONE));
				}
			} else{
				result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_PARAM_ONSTART_USERID);
				result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_PARAM_ONSTART_USERID));
			}
		} catch(Throwable t){
			if(logger.isErrorEnabled())
				logger.error("Exception in onstart", t);
			result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_PARAM_ERROR);
		}
		
		
		return result;
	}

	public OnstartBiz getOnstartBiz() {
		return onstartBiz;
	}

	public void setOnstartBiz(OnstartBiz onstartBiz) {
		this.onstartBiz = onstartBiz;
	}

}
