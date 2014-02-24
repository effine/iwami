package com.iwami.iwami.app.biz;

import java.util.Map;

public interface GiftHistoryBiz {

	boolean getUseridStatus(long userid);
	
	Map<String,Object> getData(long userid);
	
}
