package com.excilys.formation.projet.om;

public class Log {
	private long id;
	private String description;

	private Computer computer;
	
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
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", description=" + description + ", computer="
				+ computer + "]";
	}
}
