package com.excilys.formation.projet.om;

import java.io.Serializable;
import java.util.Date;

import com.excilys.formation.projet.util.Constant;
/**
 * Class representing a computer 
 * @author excilys
 *
 */
public class Computer implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;



	/**
	 * Builder constructor of computer
	 * @param b
	 */
	private Computer(Builder b){
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
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
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
	 * @author excilys
	 *
	 */
	public static class Builder{
		private long id;
		private String name;
		private Date introduced;
		private Date discontinued;
		private Company company;
		
		public Builder(){
			this.setId(0);
			this.setName(Constant.UNKNOWN);
		}
		
		public Builder id(long id){
			this.setId(id);
			return this;
		}
		public Builder name(String name){
			this.setName(name);
			return this;
		}
		public Builder introduced(Date introduced){
			this.setIntroduced(introduced);
			return this;
		}
		public Builder discontinued(Date discontinued){
			this.setDiscontinued(discontinued);
			return this;
		}
		public Builder company(Company company){
			this.setCompany(company);
			return this;
		}
		
		public Computer build(){
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

		public Date getIntroduced() {
			return introduced;
		}

		public void setIntroduced(Date introduced) {
			this.introduced = introduced;
		}

		public Date getDiscontinued() {
			return discontinued;
		}

		public void setDiscontinued(Date discontinued) {
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
