package com.iwami.iwami.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwami.iwami.app.constants.SqlConstants;
import com.iwami.iwami.app.dao.UserDao;
import com.iwami.iwami.app.model.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Override
	public User getUserById(long id) {
		List<User> users = getJdbcTemplate().query("select id, current_prize, exchange_prize, new_prize, cont_prize, comment_prize, last_cell_phone_1, last_alipay_account, last_bank_account, "
				+ "last_bank_name, last_address, last_cell_phone_2, last_name, name, uuid, cell_phone, age, gender, job, address from " 
				+ SqlConstants.TABLE_USER + " a join " + SqlConstants.TABLE_USERINFO + " b on a.id = b.userid where a.id = ? and a.isdel = 0 and b.isdel = 0", new Object[]{id}, new RowMapper<User>(){

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
			
		});
		if(users != null && users.size() > 0)
			return users.get(0);
		else
			return null;
	}

	@Override
	public boolean subUserCurrentPrize4Draw(long userid, int prize) {
		int count = getJdbcTemplate().update("update " + SqlConstants.TABLE_USER + " set current_prize = current_prize - ? where id = ? and current_prize >= ? and isdel = 0", new Object[]{prize, userid, prize});
		if(count > 0)
			return true;
		else
			return false;
	}

}
