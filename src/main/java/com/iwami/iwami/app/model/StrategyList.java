package com.iwami.iwami.app.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class StrategyList {

	private long id;
	private String name;
	private String subname;
	private String intr;
	private int rank;
	private String iconSmall;
	private String iconBig;
	private long lastmodTime;
	private int lastmodUserid;
	private int isdel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getIntr() {
		return intr;
	}

	public void setIntr(String intr) {
		this.intr = intr;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getIconSmall() {
		return iconSmall;
	}

	public void setIconSmall(String iconSmall) {
		this.iconSmall = iconSmall;
	}

	public String getIconBig() {
		return iconBig;
	}

	public void setIconBig(String iconBig) {
		this.iconBig = iconBig;
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
