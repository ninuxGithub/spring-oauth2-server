package com.hundsun.oauth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.User;

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = -4819743308196695758L;

	protected static final String ROLE_PREFFIX = "ROLE_";
	protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(
			ROLE_PREFFIX + Privilege.USER.name());

	protected User user;

	protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	public UserDetail() {
	}

	public UserDetail(User user) {
		this.user = user;
		initAuthorities();
	}

	private void initAuthorities() {
		grantedAuthorities.add(DEFAULT_USER_ROLE);

		if (user.isDefaultUser()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFFIX + Privilege.MOBILE.name()));
			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFFIX + Privilege.UNITY.name()));
		} else {
			List<Privilege> list = user.getPriviliges();
			for (Privilege privilege : list) {
				grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFFIX + privilege.name()));
			}
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDetail [user=" + user + ", grantedAuthorities=" + grantedAuthorities + "]";
	}

}
