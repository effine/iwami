package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.ExchangeDao;
import com.iwami.iwami.app.dao.PresentDao;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.Present;
import com.iwami.iwami.app.service.GiftExchangeService;

public class GiftExchangeServiceImpl implements GiftExchangeService {

	private ExchangeDao exchangeDao;
	private UserDao userDao;
	private PresentDao presentDao;

	public ExchangeDao getExchangeDao() {
		return exchangeDao;
	}

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	@Override
	public boolean addExchangeList(List<Exchange> list) {
		return exchangeDao.addExchangeList(list);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public PresentDao getPresentDao() {
		return presentDao;
	}

	public void setPresentDao(PresentDao presentDao) {
		this.presentDao = presentDao;
	}

	@Override
	public boolean subCurrPrize(long userid, int prize) {
		return userDao.subCurrPrize(userid, prize);
	}

	@Override
	public boolean addPrize(long userid, int prize) {
		return exchangeDao.addPrize(userid, prize);
	}

	@Override
	public boolean getUseridStatus(long userid) {
		return userDao.getUseridStatus(userid);
	}

	@Override
	public Present getPresent(long presentId) {
		return presentDao.getIdStatus(presentId);
	}

	@Override
	public int getCurrPrize(long userid) {
		return userDao.getPrize(userid).getCurrentPrize();
	}
}
