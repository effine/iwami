package com.iwami.iwami.app.dao;

import com.iwami.iwami.app.model.User;

public interface UserDao {
	
	public User getUserById(long id);

	public boolean subUserCurrentPrize4Draw(long userid, int prize);

}
