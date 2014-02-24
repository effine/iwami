package com.iwami.iwami.app.biz;

import java.util.Map;

public interface PresentGiftBiz {
	
	boolean getUseridStatus(long userid);
	
	boolean getCellphoneStatus(long cellPhone);
	
	boolean getPrizeStatus(int prize,long userid);
	
	Map<String,Object> getData(long userid,long cellPhone,int prize);

}
