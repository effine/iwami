package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.ApkDao;
import com.iwami.iwami.app.model.Apk;
import com.iwami.iwami.app.service.ApkService;

public class ApkServiceImpl implements ApkService {

	private ApkDao apkDao;
	
	@Override
	public Apk getApk() {
		return apkDao.getApk();
	}

	public ApkDao getApkDao() {
		return apkDao;
	}

	public void setApkDao(ApkDao apkDao) {
		this.apkDao = apkDao;
	}

}
