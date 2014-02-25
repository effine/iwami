package com.iwami.iwami.app.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.StrategyImagesBiz;
import com.iwami.iwami.app.biz.StrategyListBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;
import com.iwami.iwami.app.model.StrategyImages;
import com.iwami.iwami.app.model.StrategyList;

@AjaxClass
public class StrategyListAjax {

	private Log logger = LogFactory.getLog(getClass());
	
	private StrategyListBiz strategyListBiz;
	private StrategyImagesBiz strategyImagesBiz;
	
	@AjaxMethod(path = "strategy/list.ajax")
	public Map<Object,Object> strategyList(){
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		List<Map<String,Object>> beanList = new ArrayList<Map<String,Object>>();
		Map<String,Object> beanMap = new HashMap<String,Object>();
		
		List<Map<String,Object>> imgList = new ArrayList<Map<String,Object>>();
		Map<String,Object> imgMap = new HashMap<String,Object>();
		
		try{
			List<StrategyImages> img = strategyImagesBiz.getStratrgyImages();
			List<StrategyList> list = strategyListBiz.getStragyList();
			
			if(img != null && img.size() >0 && list != null && list.size() > 0 ){
				for(StrategyImages si: img){
					imgMap.put("id", si.getId());
					imgMap.put("rank", si.getRank());
					imgMap.put("url", si.getIconUrl());
					imgList.add(imgMap);
				}
				
				for(StrategyList sl: list){
					beanMap.put("id", sl.getId());
					beanMap.put("name", sl.getName());
					beanMap.put("subname", sl.getSubname());
					beanMap.put("intr", sl.getIntr());
					beanMap.put("rank", sl.getRank());
					beanMap.put("iconSmall", sl.getIconSmall());
					beanMap.put("iconBig", sl.getIconBig());
					beanList.add(beanMap);
				}
				result.put("images", imgList);
				result.put("list", beanList);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_OK);
			}else{
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}catch(Throwable t){
			if(logger.isErrorEnabled()){
				logger.error("Exception in strategy/list ",t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public StrategyListBiz getStrategyListBiz() {
		return strategyListBiz;
	}

	public void setStrategyListBiz(StrategyListBiz strategyListBiz) {
		this.strategyListBiz = strategyListBiz;
	}

	public StrategyImagesBiz getStrategyImagesBiz() {
		return strategyImagesBiz;
	}

	public void setStrategyImagesBiz(StrategyImagesBiz strategyImagesBiz) {
		this.strategyImagesBiz = strategyImagesBiz;
	}

}