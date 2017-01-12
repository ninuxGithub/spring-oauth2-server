package com.hundsun.oauth.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hundsun.oauth.domain.User;

/**
 * @function:
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.rowmapper.UserRowMapper.java 类全路径
 * @2016年12月30日 下午3:48:10
 * @UserRowMapper
 *
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet re, int value) throws SQLException {
		User user = new User();
		user.setClientId(re.getString("client_id"));
		user.setId(re.getLong("id"));
		user.setGuid(re.getString("guid"));
		user.setArchived(re.getBoolean("archived"));
		user.setCreateTime(re.getTimestamp("create_time").toLocalDateTime());
		user.setEmail(re.getString("email"));
		user.setPhone(re.getString("phone"));
		user.setPassword(re.getString("password"));
		user.setUsername(re.getString("username"));
		user.setLastLoginTime(re.getTimestamp("last_login_time"));
		return user;
	}

}
