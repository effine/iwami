package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.UserBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;
import com.iwami.iwami.app.constants.IWamiConstants;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.util.TwoDCodeUtil;

@AjaxClass
public class UserAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private UserBiz userBiz;

	@AjaxMethod(path = "userinfo.ajax")
	public Map<Object, Object> download(Map<String, String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		try{
			User user = null;
			
			if(params.containsKey("userid")){
				long userid = NumberUtils.toLong(params.get("userid"));
				user = userBiz.getUserById(userid);
			}
			
			if(user != null){
				result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_OK);
				result.put("userid", user.getId());
				result.put("2Dcode", TwoDCodeUtil.get2DCode(user.getId() + IWamiConstants.TWO_DIMENSION_CODE_SEPERATOR + user.getName() + IWamiConstants.TWO_DIMENSION_CODE_SEPERATOR + user.getCellPhone()));
				result.put("username", user.getName());
				result.put("uuid", user.getUuid());
				result.put("cellPhone", user.getCellPhone());
				result.put("age", user.getAge());
				result.put("job", user.getJob());
				result.put("address", user.getAddress());
				result.put("currentPrize", user.getCurrentPrize());
				result.put("exchangePrize", user.getExchangePrize());
				result.put("newPrize", user.getNewPrize());
				result.put("contPrize", user.getContPrize());
				result.put("commentPrize", user.getCommentPrize());
				result.put("lastCellPhone1", user.getLastCellPhone1());
				result.put("lastAlipayAccount：", user.getLastAlipayAccount());
				result.put("lastBankAccount：", user.getLastBankAccount());
				result.put("lastBankName", user.getLastBankName());
				result.put("lastAddress", user.getLastAddres());
				result.put("lastCellPhone2", user.getLastCellPhone2());
				result.put("lastName", user.getLastName());
			} else{
				result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_ERROR_USERINFO_USERID);
				result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_USERINFO_USERID));
			}
		} catch(Throwable t){
			if(logger.isErrorEnabled())
				logger.error("Exception in Userinfo", t);
			result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_ERROR);
		}
		
		
		return result;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

}
