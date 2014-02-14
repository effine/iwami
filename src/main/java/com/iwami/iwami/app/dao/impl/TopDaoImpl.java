package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.dao.TopDao;
import com.iwami.iwami.app.model.Top;

public class TopDaoImpl extends JdbcDaoSupport implements TopDao {

	@Override
	public List<Top> getTop() {
		/* TODO type为金榜任务，二进制表示，参考数据库设计task表  */
		String sql ="select * from task where type = 01000000 order by rank ";
		List<Top> list = getJdbcTemplate().query(sql,new RowMapper<Top>(){
			@Override
			public Top mapRow(ResultSet rs, int index) throws SQLException {
				Top top = new Top();
				top.setId(rs.getInt("id"));
				top.setName(rs.getString("name"));
				top.setRank(rs.getInt("rank"));
				top.setSize(rs.getDouble("size"));
				top.setIntr(rs.getString("intr"));
				top.setPrize(rs.getInt("prize"));
				top.setAvailable(rs.getInt("available"));
				top.setBackground(rs.getInt("background"));
				top.setRegister(rs.getInt("register"));
				top.setTime(rs.getInt("time"));
				top.setStar(rs.getInt("star"));
				top.setReputation(rs.getInt("reputation"));
				top.setIconSmall(rs.getString("iconSmall"));
				top.setIconBig(rs.getString("iconBig"));
				return top;
			}
		});
			return list;
	} 

	@Override
	public Long getUpdateTime() {
		return null;
	}
}
