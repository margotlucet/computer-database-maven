package com.excilys.formation.projet.om;

import java.io.Serializable;

import com.excilys.formation.projet.util.Constant;
/**
 * Class representing a company
 * @author excilys
 *
 */
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	/**
	 * Default constructor of company
	 */
	public Company(){
		this.setId(0);
		this.setName(Constant.UNKNOWN);
	}
	/**
	 * Builder contructor of company
	 * @param b
	 */
	private Company(Builder b){
		this.setId(b.getId());
		this.setName(b.getName());
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
	/**
	 * Builder of company
	 * @author excilys
	 *
	 */
	public static class Builder{
		private long id;
		private String name;

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

		public Company build(){
			return new Company(this);
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


	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}


}
