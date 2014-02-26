package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.ExchangeDao;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.service.PresentGiftService;

public class PresentGiftServiceImpl implements PresentGiftService {

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
	public boolean getUseridStatus(long userid) {
		return userDao.getUseridStatus(userid);
	}

	@Override
	public boolean getCellphoneStatus(long cellPhone) {
		return userDao.getCellphoneStatus(cellPhone);
	}

	@Override
	public int getCurrPrize(long userid) {
		return userDao.getUser(userid).getCurrentPrize();
	}

	@Override
	public User getUser(long cellPhone) {
		return userDao.getUserByCellphone(cellPhone);
	}

	@Override
	public boolean addExchange(Exchange ex) {
		return exchangeDao.addExchange(ex);
	}

	@Override
	public int subPrize(long userid,int prize) {
		return userDao.subCurrPrize(userid, prize);
	}

	@Override
	public boolean addPrize(long userid, int prize) {
		return userDao.addCurrPrize(userid, prize);
	}
}
