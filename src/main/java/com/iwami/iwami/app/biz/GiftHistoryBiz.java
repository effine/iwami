package com.iwami.iwami.app.biz;

import java.util.Map;

public interface GiftHistoryBiz {

	boolean getIdStatus(long userid);
	
	Map<String,Object> getData();
	
}
