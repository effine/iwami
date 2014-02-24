package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.Exchange;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.service.PresentGiftService;

public class PresentGiftServiceImpl implements PresentGiftService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
		return userDao.getPrize(userid).getCurrentPrize();
	}

	@Override
	public User getUser(long cellPhone) {
		return userDao.getUserByCellphone(cellPhone);
	}

	@Override
	public boolean addExchange(Exchange ex) {
		return false;
	}

}
