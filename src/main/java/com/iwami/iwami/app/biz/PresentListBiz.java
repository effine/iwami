package com.iwami.iwami.app.biz;

import java.util.Map;

public interface PresentListBiz {
	
	Map<String,Object> getData(long userid,int start,int step);

}
