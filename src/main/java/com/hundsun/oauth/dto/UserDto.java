package com.hundsun.oauth.dto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.User;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -1824299969397259956L;

	protected Long id;
	protected String clientId;
	protected String guid;
	protected String username;
	protected String phone;
	protected String email;
	protected String createTime;
	protected List<Privilege> privileges = new ArrayList<>();

	public UserDto() {
	}

	/**
	 * 以user 入参 构造 userDto
	 * 
	 * @param user
	 */
	public UserDto(User user) {
		this.id = user.getId();
		this.clientId= user.getClientId();
		this.guid = user.getGuid();
		this.username = user.getUsername();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.privileges = user.getPrivileges();
		this.createTime = user.getCreateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * from User to UserDto
	 * 
	 * @param users
	 * @return
	 */
	public static List<UserDto> toDtos(List<User> users) {
		List<UserDto> dtos = new ArrayList<>(users.size());
		for (User user : users) {
			dtos.add(new UserDto(user));
		}
		return dtos;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", clientId=" + clientId + ", guid=" + guid + ", username=" + username + ", phone="
				+ phone + ", email=" + email + ", createTime=" + createTime + ", privileges=" + privileges + "]";
	}

}
