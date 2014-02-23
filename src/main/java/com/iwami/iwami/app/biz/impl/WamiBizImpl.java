package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.WamiBiz;
import com.iwami.iwami.app.model.Wami;
import com.iwami.iwami.app.service.WamiService;

public class WamiBizImpl implements WamiBiz {

	private WamiService wamiService;

	@Override
	public boolean getIdStatus(long taskid) {
		return wamiService.getIdStatus(taskid);
	}

	@Override
	public boolean getType(int type) {
		boolean flag = false;
		/*  挖米类型: 0下载开始，1下载完成，2安装完成，3启动运行，4关闭/任务完成  */		
		for(int i=0;i<=4;i++){
			if(i == type)
				flag =  true;
			else
				flag = false;
		}
		return flag;
	}

	@Override
	public boolean getUseridStatus(int userid) {
		return wamiService.getUseridStatus(userid);
	}

	@Override
	public boolean statusUplod(int userid, long taskid, int type, long time,String channel) {
		Wami wami = new Wami();
		wami.setUserid(userid);
		wami.setTaskId(taskid);
		wami.setType(type);
		wami.setAddTime(time);
		wami.setChannel(channel);
		wami.setPrize(wamiService.getPrize(taskid).getPrize());
		wami.setLastmodTime(System.currentTimeMillis());
		wami.setLastmodUserid(1);	// TODO 上次修改人id
		wami.setIsdel(0); 	// TODO 0是前端展示,1是删除，2是仅后端显示
		
		return wamiService.statusUplod(wami);
	}

	public WamiService getWamiService() {
		return wamiService;
	}

	public void setWamiService(WamiService wamiService) {
		this.wamiService = wamiService;
	}
}
