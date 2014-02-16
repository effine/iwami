
package com.iwami.iwami.app.biz.impl;

import java.util.List;

import com.iwami.iwami.app.biz.TopBiz;
import com.iwami.iwami.app.model.Top;
import com.iwami.iwami.app.service.TopService;

public class TopBizImpl implements TopBiz {

	private TopService topService;

	@Override
	public List<Top> getTop() {
		return topService.getTop();
	}
	
	public TopService getTopService() {
		return topService;
	}

	public void setTopService(TopService topService) {
		this.topService = topService;
	}

}
