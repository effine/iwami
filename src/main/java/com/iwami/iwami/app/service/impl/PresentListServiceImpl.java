package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.PresentDao;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.Present;
import com.iwami.iwami.app.service.PresentListService;

public class PresentListServiceImpl implements PresentListService {

	private UserDao userDao;
	private PresentDao presentDao;

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
	public int getPrize(long userid) {
		return userDao.getPrize(userid).getCurrentPrize();
	}

	@Override
	public List<Present> getPresent(int start, int step) {
		return presentDao.getPresent(start, step);
	}

}
