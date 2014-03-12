package com.excilys.formation.projet.om;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class Log implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "description")
	private String description;

	@Column(name = "computer_id")
	private long computerId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getComputerId() {
		return computerId;
	}

	public void setComputerId(long computerId) {
		this.computerId = computerId;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", description=" + description + ", computer="
				+ computerId + "]";
	}
}
