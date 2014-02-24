package com.iwami.iwami.app.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class RateInfo {

	private long strategyId;
	private String uuid;
	private long lastmodTime;
	private int isdel;

	public long getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(long strategyId) {
		this.strategyId = strategyId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getLastmodTime() {
		return lastmodTime;
	}

	public void setLastmodTime(long lastmodTime) {
		this.lastmodTime = lastmodTime;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
