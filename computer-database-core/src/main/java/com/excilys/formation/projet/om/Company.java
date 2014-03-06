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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
