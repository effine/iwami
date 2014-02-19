package com.iwami.iwami.app.biz.impl;

import java.util.List;

import com.iwami.iwami.app.biz.StrategyImagesBiz;
import com.iwami.iwami.app.model.StrategyImages;
import com.iwami.iwami.app.service.StrategyImagesService;

public class StrategyImagesBizImpl implements StrategyImagesBiz {

	private StrategyImagesService siService;

	@Override
	public List<StrategyImages> getStratrgyImages() {
		return siService.getStratrgyImages();
	}

	public StrategyImagesService getSiService() {
		return siService;
	}

	public void setSiService(StrategyImagesService siService) {
		this.siService = siService;
	}

}
