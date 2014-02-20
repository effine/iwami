package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;

public interface StrategyDetailDao {

	List<StrategyInfo> getStrategyInfo(int id);

	List<StrategyInfo> getStrateInfo(int id, int start, int step);

	int countStrategyInfo(int id, int start, int step);

	List<StrategyRate> getStrategyRate();

	int updateStrategyRate(int strategyId);

}
