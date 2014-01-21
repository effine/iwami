package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	@Override
	public User getUserById(long id) {
		return userDao.getUserById(id);
	}

	@Override
	public boolean subUserCurrentPrize4Draw(long userid, int prize) {
		return userDao.subUserCurrentPrize4Draw(userid, prize);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
