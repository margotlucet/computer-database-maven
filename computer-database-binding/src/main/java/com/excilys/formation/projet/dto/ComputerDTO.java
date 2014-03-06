package com.excilys.formation.projet.dto;

import com.excilys.formation.projet.util.Constant;

/**
 * Data Trasfert Object of Computer
 * 
 * @author excilys
 * 
 */
public class ComputerDTO {
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private long companyId;
	private String companyName;

	/**
	 * Default constructor of ComputerDTO
	 */
	public ComputerDTO() {
		id = 0;
		name = Constant.UNKNOWN;
		introduced = Constant.UNKNOWN;
		discontinued = Constant.UNKNOWN;
		companyId = 0;
		companyName = Constant.UNKNOWN;
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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + ", companyName=" + companyName
				+ "]";
	}

	private ComputerDTO(Builder b) {
		this.setName(b.getName());
		this.setId(b.getId());
		this.setIntroduced(b.getIntroduced());
		this.setDiscontinued(b.getDiscontinued());
		this.setCompanyId(b.getCompanyId());
		this.setCompanyName(b.getCompanyName());
	}

	/**
	 * Builder of ComputerDTO
	 * 
	 * @author excilys
	 * 
	 */
	public static class Builder {
		private long id = 0;
		private String name;
		private String introduced;
		private String discontinued;
		private long companyId;
		private String companyName;

		public Builder() {
			id = 0;
			name = Constant.UNKNOWN;
			introduced = Constant.UNKNOWN;
			discontinued = Constant.UNKNOWN;
			companyId = 0;
			companyName = Constant.UNKNOWN;
		}

		public Builder id(long id) {
			this.setId(id);
			return this;
		}

		public Builder name(String name) {
			this.setName(name);
			return this;
		}

		public Builder introduced(String introduced) {
			this.setIntroduced(introduced);
			return this;
		}

		public Builder discontinued(String discontinued) {
			this.setDiscontinued(discontinued);
			return this;
		}

		public Builder companyId(long companyId) {
			this.setCompanyId(companyId);
			return this;
		}

		public Builder companyName(String companyName) {
			this.setCompanyName(companyName);
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
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

		public String getIntroduced() {
			return introduced;
		}

		public void setIntroduced(String introduced) {
			this.introduced = introduced;
		}

		public String getDiscontinued() {
			return discontinued;
		}

		public void setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
		}

		public long getCompanyId() {
			return companyId;
		}

		public void setCompanyId(long companyId) {
			this.companyId = companyId;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

	}

}
