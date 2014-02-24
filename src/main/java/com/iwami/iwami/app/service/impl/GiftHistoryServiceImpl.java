package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.ExchangeDao;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.service.GiftHistoryService;

public class GiftHistoryServiceImpl implements GiftHistoryService {

	private UserDao userDao;
	private ExchangeDao exchangeDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ExchangeDao getExchangeDao() {
		return exchangeDao;
	}

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	@Override
	public User getExchangePrize(long userid) {
		return userDao.getPrize(userid);
	}

	@Override
	public List<Exchange> getExchangeList(long userid, int type) {
		return exchangeDao.getExchangeList(userid, type);
	}

	@Override
	public boolean getUseridStatus(long userid) {
		return userDao.getUseridStatus(userid);
	}
}
