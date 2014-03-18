package com.excilys.formation.projet.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable, GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_role_id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "authority")
	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

}
