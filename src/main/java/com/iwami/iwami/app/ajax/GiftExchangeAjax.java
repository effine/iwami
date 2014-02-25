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
	public Map<Object, Object> statusUpload(Map<String,Object> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		try {
			boolean isContains = params.containsKey("userid") && params.containsKey("ids") && params.containsKey("count")
							  && params.containsKey("cellPhone") && params.containsKey("aliAccount") && params.containsKey("prize")
							  && params.containsKey("address") && params.containsKey("name") && params.containsKey("bankName")
							  && params.containsKey("bankAccount");
			if(isContains){
				long userid = Long.parseLong(params.get("userid").toString());
				int[] ids = (int[])params.get("ids");
				int[] count = (int[])params.get("count");
				long cellPhone = Long.parseLong(params.get("cellPhone").toString());
				String aliAccount = params.get("aliAccount").toString();
				int prize = Integer.parseInt(params.get("prize").toString());
				String address = params.get("address").toString();
				String name = params.get("name").toString();
				String bankName = params.get("bankName").toString();
				long bankAccount = Long.parseLong(params.get("bankAccount").toString());
				
				if(userid != 0 && giftExchangeBiz.getUseridStatus(userid)){
					if(ids.length != 0 && giftExchangeBiz.getGiftidStatus(ids)){
						if(count.length != 0 && giftExchangeBiz.getCountStatus(count)){
							if(giftExchangeBiz.getPrizeStatus(ids, count, userid)){
								if(cellPhone != 0){
									if("".equals(aliAccount.trim())){
										if(prize <= 0){
											if("".equals(address.trim())){
												if("".equals(name.trim())){
													if("".equals(bankName)){
														if(bankAccount != 0){
															giftExchangeBiz.updateData(userid,ids,count,cellPhone,aliAccount,prize,address,name,bankName,bankAccount);
															result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
														}else
															result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_BANKACCOUNT);
															result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_BANKACCOUNT));
													}else
														result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_BANKNAME);
														result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_BANKNAME));
												}else
													result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_NAME);
													result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_NAME));
											}else
												result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_ADDRESS);
												result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_ADDRESS));
										}else
											result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_PICKUP_PRIZE);
											result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_PICKUP_PRIZE));
									}else
										result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_ALIACCOUNT);
										result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_ALIACCOUNT));
								}else
									result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_CELLPHONE);
									result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_CELLPHONE));
							}else
								result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_PRIZE);
								result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_PRIZE));
						}else
							result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_COUNT);
							result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_COUNT));
					}else
						result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_GIFT);
						result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_GIFT));
				}else
					result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_USERID);
					result.put(ErrorCodeConstants.MSG_KEY, ErrorCodeConstants.ERROR_MSG_MAP.get(ErrorCodeConstants.STATUS_ERROR_GIFT_EXCHANGE_USERID));
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
