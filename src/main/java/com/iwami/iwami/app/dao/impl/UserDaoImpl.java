package com.iwami.iwami.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.Code;
import com.iwami.iwami.app.model.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Override
	public User getUserById(long id) {
		List<User> users = getJdbcTemplate().query("select id, current_prize, exchange_prize, new_prize, cont_prize, comment_prize, last_cell_phone_1, last_alipay_account, last_bank_account, "
				+ "last_bank_name, last_address, last_cell_phone_2, last_name, name, uuid, cell_phone, age, gender, job, address from " 
				+ SqlConstants.TABLE_USER + " a join " + SqlConstants.TABLE_USERINFO + " b on a.id = b.userid where a.id = ? and a.isdel = 0 and b.isdel = 0", new Object[]{id}, new UserRowMapper());
		if(users != null && users.size() > 0)
			return users.get(0);
		else
			return null;
	}

	@Override
	public User getUserByCellPhone(long cellPhone) {
		List<User> users = getJdbcTemplate().query("select id, current_prize, exchange_prize, new_prize, cont_prize, comment_prize, last_cell_phone_1, last_alipay_account, last_bank_account, "
				+ "last_bank_name, last_address, last_cell_phone_2, last_name, name, uuid, cell_phone, age, gender, job, address from " 
				+ SqlConstants.TABLE_USER + " a join " + SqlConstants.TABLE_USERINFO + " b on a.id = b.userid where b.cell_phone = ? and a.isdel = 0 and b.isdel = 0", new Object[]{cellPhone}, new UserRowMapper());
		if(users != null && users.size() > 0)
			return users.get(0);
		else
			return null;
	}

	@Override
	public boolean newUser4Register(User user) {
		KeyHolder holder = new GeneratedKeyHolder();
	    int result = getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("insert into " + SqlConstants.TABLE_USER + "(current_prize, exchange_prize, new_prize, cont_prize, comment_prize, lastmod_time, lastmod_userid, isdel) values(0, 0, 0, 0, 0, now(), 0, 0)", Statement.RETURN_GENERATED_KEYS);
			}
		}, holder);
	    
		if(result >= 0){
			user.setId(holder.getKey().longValue());
			return true;
		}
		return false;
	}

	@Override
	public boolean addUserInfo4Register(User user) {
		int count = getJdbcTemplate().update("insert into " + SqlConstants.TABLE_USERINFO + "(userid, name, uuid, cell_phone, add_time, lastmod_time, lastmod_userid, isdel) values(?, ?, ?, ?, now(), now(), ?, 0)", new Object[]{user.getId(), user.getName(), user.getUuid(), user.getCellPhone(), user.getId()});
		if(count > 0)
			return true;
		else
			return false;
	}

	public boolean updateUser4Register(User user){
		int count = getJdbcTemplate().update("update " + SqlConstants.TABLE_USERINFO + " set name = ?, uuid = ?, cell_phone = ?, lastmod_time = now(), lastmod_userid = ? where userid = ?", new Object[]{user.getName(), user.getUuid(), user.getCellPhone(), user.getId(), user.getId()});
		if(count > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean modifyUserInfo4Register(User user) {
		int count = getJdbcTemplate().update("update " + SqlConstants.TABLE_USERINFO + " set age = ?, gender = ?, job = ?, address = ?, lastmod_time = now(), lastmod_userid = ? where userid = ?", new Object[]{user.getAge(), user.getGender(), user.getJob(), user.getAddress(), user.getId(), user.getId()});
		if(count > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean subUserCurrentPrize4Draw(long userid, int prize) {
		int count = getJdbcTemplate().update("update " + SqlConstants.TABLE_USER + " set current_prize = current_prize - ? where id = ? and current_prize >= ? and isdel = 0", new Object[]{prize, userid, prize});
		if(count > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean addCode(Code code) {
		int count = getJdbcTemplate().update("insert into " + SqlConstants.TABLE_CODE + "(userid, cell_phone, code, add_time) values(?, ?, ?, now())", new Object[]{code.getUserid(), code.getCellPhone(), code.getCode()});
		if(count > 0)
			return true;
		else
			return false;
	}

	@Override
	public Code getCode(long cellPhone, String code, Date start) {
		List<Code> codes = getJdbcTemplate().query("select userid, cell_phone, code, add_time from " + SqlConstants.TABLE_CODE + " where cell_phone = ? and code = ? and add_time >= ? order by add_time desc limit 1", 
				new Object[]{cellPhone, code, start}, new RowMapper<Code>(){

					@Override
					public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
						Code ctmp = new Code();
						ctmp.setUserid(rs.getLong("userid"));
						ctmp.setCellPhone(rs.getLong("cell_phone"));
						ctmp.setCode(rs.getString("code"));
						ctmp.setAddTime(rs.getDate("add_time"));
						return ctmp;
					}
			
		});
		if(codes != null && codes.size() > 0)
			return codes.get(0);
		else
			return null;
	}

	@Override
	public boolean getUseridStatus(long userid) {
		String sql = "select * from " + SqlConstants.TABLE_USERINFO + " where userid = ?";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{userid});
		if(line > 0)
			return true;
		else
			return false;
	}

	@Override
	public User getUser(long userid) {
		String sql = "select * from " + SqlConstants.TABLE_USER + " where id = ?";
		List<User> list =  getJdbcTemplate().query(sql,new Object[]{userid},new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setCurrentPrize(rs.getInt("current_prize"));
				user.setExchangePrize(rs.getInt("exchange_prize"));
				return user;
			}
		});
		
		if(list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public boolean getCellphoneStatus(long cellPhone) {
		String sql = "select * from " + SqlConstants.TABLE_USERINFO + " where cell_phone = ?";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{cellPhone});
		if(line > 0)
			return true;
		else
			return false;
	}

	@Override
	public User getUserByCellphone(long cellPhone) {
		String sql = "select * from " + SqlConstants.TABLE_USERINFO + " where cell_phone = ?";
		List<User> list = getJdbcTemplate().query(sql, new Object[]{cellPhone},new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setUserid(rs.getLong("userid"));
				user.setName(rs.getString("name"));
				return user;
			}
		});
		
		if(list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public int subCurrPrize(long userid, int prize) {
		String sql = "update " + SqlConstants.TABLE_USER + " set current_prize = current_prize - ? where id = ? and current_prize >= ? and isdel = 0";
		return getJdbcTemplate().queryForInt(sql,new Object[]{prize,userid,prize});
	}

	@Override
	public boolean addCurrPrize(long userid, int prize) {
		String sql = "update " + SqlConstants.TABLE_USER + " set current_prize = current_prize + ? where id = ?";
		int line = getJdbcTemplate().queryForInt(sql,new Object[]{prize,userid});
		if(line >0)
			return true;
		else
			return false;
	}
}

class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setAddress(rs.getString("address"));
		user.setAge(rs.getInt("age"));
		user.setCellPhone(rs.getLong("cell_phone"));
		user.setCommentPrize(rs.getInt("comment_prize"));
		user.setContPrize(rs.getInt("cont_prize"));
		user.setCurrentPrize(rs.getInt("current_prize"));
		user.setExchangePrize(rs.getInt("exchange_prize"));
		user.setGender(rs.getInt("gender"));
		user.setId(rs.getLong("id"));
		user.setJob(rs.getString("job"));
		user.setLastAddres(rs.getString("last_address"));
		user.setLastAlipayAccount(rs.getString("last_alipay_account"));
		user.setLastBankAccount(rs.getLong("last_bank_account"));
		user.setLastBankName(rs.getString("last_bank_name"));
		user.setLastCellPhone1(rs.getLong("last_cell_phone_1"));
		user.setLastCellPhone2(rs.getLong("last_cell_phone_2"));
		user.setLastName(rs.getString("last_name"));
		user.setName(rs.getString("name"));
		user.setNewPrize(rs.getInt("new_prize"));
		user.setUuid(rs.getString("uuid"));
		return user;
	}
	
}
