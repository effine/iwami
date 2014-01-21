package com.iwami.iwami.app.service;

import com.iwami.iwami.app.model.User;

public interface UserService {

	public User getUserById(long id);
	
	public boolean subUserCurrentPrize4Draw(long userid, int prize);
}
