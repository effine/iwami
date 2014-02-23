package com.iwami.iwami.app.biz;

public interface WamiBiz {

	boolean getIdStatus(long taskid);
	
	boolean getType(int type);
	
	boolean getUseridStatus(int userid);
	
	boolean statusUplod(int userid,long taskid,int type,long time,String channel);
}
