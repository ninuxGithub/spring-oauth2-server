package com.hundsun.oauth.dto;

import java.time.format.DateTimeFormatter;

import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.utils.GuidGenerator;
import com.hundsun.oauth.utils.PasswordHandler;

public class UserFormDto extends UserDto {

	private static final long serialVersionUID = 1982181101294470051L;

	private String password;

	public UserFormDto() {
	}
	
	public UserFormDto(User user){
		this.clientId = user.getClientId();
		this.id=user.getId();
		this.guid = user.getGuid();
		this.username = user.getUsername();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.privileges = user.getPrivileges();
		this.createTime = user.getCreateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		this.password = user.getPassword();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Privilege[] getAllPrivileges() {
		return new Privilege[] { Privilege.MOBILE, Privilege.UNITY };
	}

	public User newUser() {
		User user = new User();
		user.setId(getId());
		if(null == getGuid()){
			user.setGuid(GuidGenerator.generate());
		}else{
			user.setGuid(getGuid());
		}
		user.setClientId(getClientId());
		user.setUsername(getUsername());
		user.setPhone(getPhone());
		user.setPassword(PasswordHandler.md5(getPassword()));
		user.setEmail(getEmail());
		user.getPrivileges().addAll(getPrivileges());
		return user;

	}

	@Override
	public String toString() {
		return "UserFormDto [password=" + password + "]" + super.toString();
	}
	
	

}
