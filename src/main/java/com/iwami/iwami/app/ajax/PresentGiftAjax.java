package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.PresentGiftBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class PresentGiftAjax {

	private Log logger = LogFactory.getLog(getClass());
	private PresentGiftBiz presentGiftBiz;
	
	@AjaxMethod(path = "present/gift.ajax")
	public Map<Object, Object> statusUpload(Map<String,String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			boolean isExist = params.containsKey("userid") && params.containsKey("cellPhone") && params.containsKey("prize");
			if(isExist){
				long userid = Long.parseLong(params.get("userid"));
				long cellPhone = Long.parseLong(params.get("cellPhone"));
				int prize = Integer.parseInt(params.get("prize"));
				
				if(presentGiftBiz.getUseridStatus(userid)){
					if(presentGiftBiz.getCellphoneStatus(cellPhone)){
						if(presentGiftBiz.getPrizeStatus(prize,userid)){
							result.putAll(presentGiftBiz.getData(userid,cellPhone,prize));
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
						}else
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_PRESENT_GIFT_PRIZE);
							result.put(ErrorCodeConstants.MSG_KEY,ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_PRESENT_GIFT_PRIZE));
					}else
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_PRESENT_GIFT_CELLPHONE);
						result.put(ErrorCodeConstants.MSG_KEY,ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_PRESENT_GIFT_CELLPHONE));
				}else
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_PRESENT_GIFT_USERID);
					result.put(ErrorCodeConstants.MSG_KEY,ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_PRESENT_GIFT_USERID));
			}else
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_PARAM_ERROR);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in present/gift ", t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public PresentGiftBiz getPresentGiftBiz() {
		return presentGiftBiz;
	}

	public void setPresentGiftBiz(PresentGiftBiz presentGiftBiz) {
		this.presentGiftBiz = presentGiftBiz;
	}
}
