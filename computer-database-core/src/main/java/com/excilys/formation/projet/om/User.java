package com.excilys.formation.projet.om;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author margot
 * 
 */
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int id;

	@NotEmpty
	@Column(name = "username")
	private String username;

	@NotEmpty
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "user_role_id") })
	private List<UserRole> authorities;

	public User() {

	}

	public User(Builder b) {
		this.setId(b.getId());
		this.setUsername(b.getUsername());
		this.setPassword(b.getPassword());
		this.setEnabled(b.getEnabled());
		this.setAuthorities(b.getAuthorities());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(List<UserRole> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
		return enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + "]";
	}

	public static class Builder {
		private int id;
		private String username;
		private String password;
		private Boolean enabled;
		private List<UserRole> authorities;

		public Builder() {
			this.setId(0);
		}

		public Builder id(int id) {
			this.setId(id);
			return this;
		}

		public Builder username(String username) {
			this.setUsername(username);
			return this;
		}

		public Builder password(String password) {
			this.setPassword(password);
			return this;
		}

		public Builder enabled(Boolean enabled) {
			this.setEnabled(enabled);
			return this;
		}

		public Builder authorities(List<UserRole> autorities) {
			this.setAuthorities(autorities);
			return this;
		}

		public List<UserRole> getAuthorities() {
			return authorities;
		}

		public void setAuthorities(List<UserRole> authorities) {
			this.authorities = authorities;
		}

		public User build() {
			return new User(this);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

	}

}
