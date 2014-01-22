package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.TipsBiz;
import com.iwami.iwami.app.service.TipsService;

public class TipsBizImpl implements TipsBiz {

	private TipsService tipsService;

	@Override
	public String getContent(int type) {
		return tipsService.getContent(type);
	}

	public TipsService getTipsService() {
		return tipsService;
	}

	public void setTipsService(TipsService tipsService) {
		this.tipsService = tipsService;
	}

}
