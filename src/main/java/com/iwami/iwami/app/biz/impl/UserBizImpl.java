package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.UserBiz;
import com.iwami.iwami.app.model.User;
import com.iwami.iwami.app.service.UserService;

public class UserBizImpl implements UserBiz {
	
	private UserService userService;
	
	public User getUserById(long userid){
		return userService.getUserById(userid);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
