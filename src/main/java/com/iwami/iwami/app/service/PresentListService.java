package com.iwami.iwami.app.service;

import java.util.List;

import com.iwami.iwami.app.model.Present;

public interface PresentListService {
	
	int getPrize(long userid);
	
	List<Present> getPresent(int start,int step);

}
