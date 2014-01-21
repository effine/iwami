package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.LuckyDao;
import com.iwami.iwami.app.model.LuckyConfig;
import com.iwami.iwami.app.model.LuckyHistory;
import com.iwami.iwami.app.model.LuckyRule;
import com.iwami.iwami.app.service.LuckyService;

public class LuckyServiceImpl implements LuckyService {

	private LuckyDao luckyDao;
	
	@Override
	public List<LuckyRule> getLuckyRules() {
		return luckyDao.getLuckyRules();
	}

	@Override
	public LuckyConfig getLuckyConfig() {
		return luckyDao.getLuckyConfig();
	}

	@Override
	public int getLuckyCountByUserid(long userid) {
		return luckyDao.getLuckyCountByUserid(userid);
	}

	@Override
	public boolean addLuckyHistory(LuckyHistory history) {
		return luckyDao.addLuckyHistory(history);
	}

	public LuckyDao getLuckyDao() {
		return luckyDao;
	}

	public void setLuckyDao(LuckyDao luckyDao) {
		this.luckyDao = luckyDao;
	}

}
