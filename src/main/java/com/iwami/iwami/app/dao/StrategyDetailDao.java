package com.iwami.iwami.app.dao;

import java.util.List;

import com.iwami.iwami.app.model.StrategyInfo;
import com.iwami.iwami.app.model.StrategyRate;

public interface StrategyDetailDao {

	List<StrategyInfo> getStatus(long strategyId);

	List<StrategyInfo> getStrategyInfo(long strategyId, int start, int step);

	int countStrategyInfo();

	List<StrategyRate> getStrategyRate(long strategyId);

	int updateStrategyRate(long strategyId);

}
