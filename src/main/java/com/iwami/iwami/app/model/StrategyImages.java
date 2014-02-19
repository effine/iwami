package com.iwami.iwami.app.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class StrategyImages {

	private long id;
	private int rank;
	private String iconUrl;
	private long lastmodTime;
	private int lastmodUserid;
	private int isdel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public long getLastmodTime() {
		return lastmodTime;
	}

	public void setLastmodTime(long lastmodTime) {
		this.lastmodTime = lastmodTime;
	}

	public int getLastmodUserid() {
		return lastmodUserid;
	}

	public void setLastmodUserid(int lastmodUserid) {
		this.lastmodUserid = lastmodUserid;
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
