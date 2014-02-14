package com.iwami.iwami.app.service;

import java.util.List;

import com.iwami.iwami.app.model.Top;

public interface TopService {
	
	public List<Top> getTop();
	
	public Long getUpdateTime();

}
