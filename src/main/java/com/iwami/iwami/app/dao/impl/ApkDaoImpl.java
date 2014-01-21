package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.ApkDao;
import com.iwami.iwami.app.model.Apk;

public class ApkDaoImpl extends JdbcDaoSupport implements ApkDao {

	@Override
	public Apk getApk() {
		List<Apk> apks = getJdbcTemplate().query("select id, version, address, lastmod_time, lastmod_userid from " + SqlConstants.TABLE_APK + " where isdel = 0 order by lastmod_time desc limit 1", new RowMapper<Apk>() {
			@Override
			public Apk mapRow(ResultSet rs, int index) throws SQLException {
				Apk apk = new Apk();
				apk.setId(rs.getLong("id"));
				apk.setVersion(rs.getInt("version"));
				apk.setUrl(rs.getString("address"));
				apk.setLastmodTime(rs.getDate("lastmod_time"));
				apk.setLastmodUserid(rs.getLong("lastmod_userid"));
				return apk;
			}
		});
		
		if(apks != null && apks.size() > 0)
			return apks.get(0);
		else
			return null;
	}

}
