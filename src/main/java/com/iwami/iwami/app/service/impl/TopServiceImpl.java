package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.TopDao;
import com.iwami.iwami.app.model.Top;
import com.iwami.iwami.app.service.TopService;

public class TopServiceImpl implements TopService{
	
	private TopDao topDao;

	@Override
	public List<Top> getTop() {
		return topDao.getTop();
	}

}
