package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.Top;

public interface TopDao {
	
	public List<Top> getTop();
	
	public Long getUpdateTime();

}
