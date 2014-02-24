package com.iwami.iwami.app.service.impl;

import com.iwami.iwami.app.dao.RateInfoDao;
import com.iwami.iwami.app.model.RateInfo;
import com.iwami.iwami.app.service.RateInfoService;

public class RateInfoServiceImpl implements RateInfoService {

	private RateInfoDao rateInfoDao;

	public RateInfoDao getRateInfoDao() {
		return rateInfoDao;
	}

	public void setRateInfoDao(RateInfoDao rateInfoDao) {
		this.rateInfoDao = rateInfoDao;
	}

	@Override
	public boolean getIdStatus(long strategyId) {
		return rateInfoDao.getIdStatus(strategyId);
	}

	@Override
	public boolean pointPraise(RateInfo rateInfo) {
		// TODO 插入rate_info表，如捕获到unique异常返回100073;返回正常则更新rate_info表（strategy_rate表skim+1），需要添加事务
		return rateInfoDao.pointPraise(rateInfo);
	}

}
