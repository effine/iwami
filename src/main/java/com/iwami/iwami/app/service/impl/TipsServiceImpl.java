package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.TipsDao;
import com.iwami.iwami.app.service.TipsService;

public class TipsServiceImpl implements TipsService {

	private TipsDao tipsDao;

	@Override
	public String getContent(int type) {
		return tipsDao.getContent(type);
	}

	public TipsDao getTipsDao() {
		return tipsDao;
	}

	public void setTipsDao(TipsDao tipsDao) {
		this.tipsDao = tipsDao;
	}

}
