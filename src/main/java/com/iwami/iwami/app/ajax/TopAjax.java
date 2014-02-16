package com.iwami.iwami.app.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.TopBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;
import com.iwami.iwami.app.model.Top;

@AjaxClass
public class TopAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private TopBiz topBiz;
	
	@AjaxMethod(path = "top.ajax")
	public Map<Object,Object> top(){
		Map<Object,Object> result = new HashMap<Object,Object>();
		try{
			List<Top> list = topBiz.getTop();
			List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
			
			Long time = null;
			
			if(list.size() > 0 && list != null){
				for(Top top: list){
					Map<String,Object> map =new HashMap<String,Object>();
					map.put("id", top.getId());
					map.put("name", top.getName());
					map.put("rank", top.getRank());
					map.put("size", top.getSize());
					map.put("intr", top.getIntr());
					map.put("prize", top.getPrize());
					map.put("available", top.getAvailable());
					map.put("background", top.getBackground());
					map.put("register", top.getRegister());
					map.put("time", top.getTime());
					map.put("star", top.getStar());
					map.put("reputation", top.getReputation());
					map.put("iconSmall", top.getIconSmall());
					map.put("iconBig", top.getIconBig());
					data.add(map);
					if(top.getLastmodTime() > time){
						time = top.getLastmodTime();
					}
				}
			}
				result.put("data", data);
				result.put("time", time);
				result.put(ErrorCodeConstants.STATUS_KEY,
						ErrorCodeConstants.STATUS_OK);
		}catch(Throwable t){
			if(logger.isErrorEnabled()){
				logger.error("Exception in top",t);
				result.put(ErrorCodeConstants.STATUS_KEY,ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public TopBiz getTopBiz() {
		return topBiz;
	}

	public void setTopBiz(TopBiz topBiz) {
		this.topBiz = topBiz;
	}
}
