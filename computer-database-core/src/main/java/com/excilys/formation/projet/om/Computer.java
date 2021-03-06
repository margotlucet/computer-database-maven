package com.excilys.formation.projet.om;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.excilys.formation.projet.util.Constant;

/**
 * Class representing a computer
 * 
 * @author excilys
 * 
 */

@Entity
@Table(name = "computer")
public class Computer implements Serializable {
	static final Logger LOGGER = LoggerFactory.getLogger(Computer.class);
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@NotEmpty
	@Column(name = "name")
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "introduced")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime introduced;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "discontinued")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime discontinued;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	public Computer() {
	}

	/**
	 * Builder constructor of computer
	 * 
	 * @param b
	 */
	private Computer(Builder b) {
		this.setId(b.getId());
		this.setName(b.getName());
		this.setIntroduced(b.getIntroduced());
		this.setDiscontinued(b.getDiscontinued());
		this.setCompany(b.getCompany());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}

	public DateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}

	/**
	 * Builder of computer
	 * 
	 * @author excilys
	 * 
	 */
	public static class Builder {
		private long id;
		private String name;
		private DateTime introduced;
		private DateTime discontinued;
		private Company company;

		public Builder() {
			this.setId(0);
			this.setName(Constant.UNKNOWN);
		}

		public Builder id(long id) {
			this.setId(id);
			return this;
		}

		public Builder name(String name) {
			this.setName(name);
			return this;
		}

		public Builder introduced(DateTime introduced) {
			this.setIntroduced(introduced);
			return this;
		}

		public Builder discontinued(DateTime discontinued) {
			this.setDiscontinued(discontinued);
			return this;
		}

		public Builder company(Company company) {
			this.setCompany(company);
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public DateTime getIntroduced() {
			return introduced;
		}

		public void setIntroduced(DateTime introduced) {
			this.introduced = introduced;
		}

		public DateTime getDiscontinued() {
			return discontinued;
		}

		public void setDiscontinued(DateTime discontinued) {
			this.discontinued = discontinued;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

	}

}
