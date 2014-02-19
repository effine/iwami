package com.iwami.iwami.app.service.impl;

import java.util.List;

import com.iwami.iwami.app.dao.StrategyImagesDao;
import com.iwami.iwami.app.model.StrategyImages;
import com.iwami.iwami.app.service.StrategyImagesService;

public class StrategyImagesServiceImpl implements StrategyImagesService {

	private StrategyImagesDao siDao;

	@Override
	public List<StrategyImages> getStratrgyImages() {
		return siDao.getStratrgyImages();
	}

	public StrategyImagesDao getSiDao() {
		return siDao;
	}

	public void setSiDao(StrategyImagesDao siDao) {
		this.siDao = siDao;
	}

}
