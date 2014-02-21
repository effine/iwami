package com.iwami.iwami.app.biz.impl;

import com.iwami.iwami.app.biz.WamiBiz;
import com.iwami.iwami.app.service.WamiService;

public class WamiBizImpl  implements WamiBiz {
	
	private WamiService wamiService;
	
	@Override
	public boolean getIdStatus(long taskid) {
		
		
		
		return false;
	}
	
	@Override
	public boolean getType() {
		// TODO Auto-generated method stub
		return false;
	}
	
	


	@Override
	public boolean getUseridStatus(int userid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getTimeStatus() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	public WamiService getWamiService() {
		return wamiService;
	}
	
	public void setWamiService(WamiService wamiService) {
		this.wamiService = wamiService;
	}
}
