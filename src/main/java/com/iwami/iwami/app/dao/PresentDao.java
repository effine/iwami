package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.Present;

public interface PresentDao {
	
	List<Present> getPresent(int start,int step);
	
	Present getIdStatus(long presentId);

}
