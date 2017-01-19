package com.hundsun.oauth.repositoryimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hundsun.oauth.constant.CacheConstants;
import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.dto.UserFormDto;
import com.hundsun.oauth.repository.UserRepository;
import com.hundsun.oauth.rowmapper.UserRowMapper;
import com.hundsun.oauth.utils.PasswordHandler;

@Repository("userRepositoryImpl")
public class UserRepositoryImpl extends RepositoryImpl<User> implements UserRepository {

	private static UserRowMapper userRowMapper = new UserRowMapper();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User findByGuid(String guid) {
		final String sql = "select * from user_ where guid = ?";
		List<User> list = jdbcTemplate.query(sql, new Object[] { guid }, userRowMapper);
		User user = null;
		if (null != list && !list.isEmpty()) {
			user = list.get(0);
			user.getPrivileges().addAll(findPrivilege(user.getId()));
		}
		return user;
	}

	/**
	 * 更加用户id获取权限的集合
	 * 
	 * @param userId
	 *            用户ID --Long 类型
	 * @return 该用户的权限集合
	 */
	public Collection<Privilege> findPrivilege(Long userId) {
		String sql = "select privilege from user_privilege where user_id = ?";
		List<String> priviStrs = jdbcTemplate.queryForList(sql, new Object[] { userId }, String.class);
		List<Privilege> privileges = new ArrayList<>(priviStrs.size());
		privileges.addAll(priviStrs.stream().map(Privilege::valueOf).collect(Collectors.toList()));
		return privileges;
	}

	@Override
	public void saveUser(User user) {
		
		// ①删除改用户的所以权限
		jdbcTemplate.update("delete from user_privilege where user_id = ?", ps -> {
			ps.setLong(1, user.getId());
		});
		
		String sql = "insert into user_ (guid, archived, create_time, email, password, username, phone, client_id, id) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, ps -> {
			ps.setString(1, user.getGuid());
			ps.setBoolean(2, user.getArchived());
			ps.setTimestamp(3, Timestamp.valueOf(user.getCreateTime()));
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getUsername());
			ps.setString(7, user.getPhone());
			ps.setString(8, user.getClientId());
			ps.setLong(9, user.getId());
		});

		Long userId = jdbcTemplate.queryForObject("select id from user_ where guid = ?",new Object[] { user.getGuid() }, Long.class);

		// 新添加的用户本身是没有id的 ，先从数据库查出添加之后产生的ID 然后赋值给 原来的用户对象
		user.setId(userId);

		// insert privileges
		addPrivileges(user);
	}

	@Override
	public void updateUser(User user) {
		String sql = "update user_ set username=?, password=? , phone=?, email = ?, client_id =? where guid= ?";
		jdbcTemplate.update(sql, ps -> {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getClientId());
			ps.setString(6, user.getGuid());
		});

		// update privilege

		// ①删除改用户的所以权限
		jdbcTemplate.update("delete  from user_privilege where user_id = ?", ps -> {
			ps.setLong(1, user.getId());
		});

		// ②添加新的权限
		addPrivileges(user);
	}

	/**
	 * 
	 * 添加对应用户的权限
	 * 
	 * @param user
	 */
	private void addPrivileges(User user) {
		if (null != user.getPrivileges() && !user.getPrivileges().isEmpty()) {
			for (Privilege p : user.getPrivileges()) {
				jdbcTemplate.update("insert into user_privilege (user_id, privilege) values (?,?) ", ps -> {
					ps.setLong(1, user.getId());
					ps.setString(2, p.name());
				});

			}
		}
	}

	@Override
	@Cacheable(value = CacheConstants.USER_CACHE, key = "#username")
	public User findByUsername(String username) {
		String sql = "select * from user_ where username = ? and archived =0 ";
		List<User> list = jdbcTemplate.query(sql, new Object[] { username }, userRowMapper);
		User user = null;
		if (null != list && !list.isEmpty()) {
			user = list.get(0);
			user.getPrivileges().addAll(findPrivilege(user.getId()));
		}
		return user;
	}

	@Override
	public List<User> findUsersByUsername(String username) {
		String sql = "select * from user_ where archived =0 ";
		Object[] params = new Object[]{};
		if (StringUtils.isNotBlank(username)) {
			sql += " and username like ? ";
			params = new Object[] { "%" + username + "%" };
		}
		sql += " order by create_time desc";

		List<User> list = jdbcTemplate.query(sql, params, userRowMapper);
		for (User user : list) {
			user.getPrivileges().addAll(findPrivilege(user.getId()));
		}
		return list;
	}

	@Override
	public User findGreaterIdUser() {
		String sql ="select * from user_ order by id desc";
		List<User> list = jdbcTemplate.query(sql, new Object[]{}, userRowMapper);
		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserById(Long id) {
		User user = jdbcTemplate.queryForObject("select * from user_ where id= ? ", new Object[]{id}, userRowMapper);
		if(null != user){
			user.getPrivileges().addAll(findPrivilege(user.getId()));
		}
		return user;
	}

	@Override
	public User findUserByNameAndPassword(String username, String password) {
		List<User> users= jdbcTemplate.query("select * from user_ where username=? and password=?", new Object[]{username,password}, userRowMapper);
		if(null != users && !users.isEmpty()){
			User user = users.get(0);
			if(null != user){
				user.getPrivileges().addAll(findPrivilege(user.getId()));
			}
			return user;
			
		}
		return null;
	}

	@Override
	public void deleteUserById(Long id) {
		try {
			jdbcTemplate.update("delete  from user_ where id = ?", ps -> {
				ps.setLong(1, id);
			});
			jdbcTemplate.update("delete  from user_privilege where user_id = ?", ps -> {
				ps.setLong(1, id);
			});
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void changePassword(UserFormDto userForm) {
		jdbcTemplate.update("update user_ set password = ? where id =?",ps->{
			ps.setString(1, PasswordHandler.md5(userForm.getRepassword()));
			ps.setLong(2, userForm.getId());
		});
	}

}
