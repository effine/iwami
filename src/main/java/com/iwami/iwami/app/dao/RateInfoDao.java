package com.iwami.iwami.app.dao;

import com.iwami.iwami.app.model.RateInfo;

public interface RateInfoDao {

	boolean getIdStatus(long strategyId);
	
	boolean pointPraise(RateInfo ri);
	
}
