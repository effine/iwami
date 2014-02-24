package com.iwami.iwami.app.service;

import com.iwami.iwami.app.model.RateInfo;

public interface RateInfoService {
	
	boolean getIdStatus(long strategyId);
	
	boolean pointPraise(RateInfo rateInfo);

}
