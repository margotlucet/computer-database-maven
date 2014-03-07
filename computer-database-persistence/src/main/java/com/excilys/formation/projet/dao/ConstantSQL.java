package com.excilys.formation.projet.dao;

public class ConstantSQL {

	public static final String CREATE = "INSERT INTO computer (name, introduced, "
			+ "discontinued, company_id) VALUES (:name, :introduced, :discontinued, :companyId)";

	public static final String SELECT_BY_ID = "SELECT computer.id, computer.name, introduced, discontinued, "
			+ "company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.id = :id ;";

	public static final String DELETE = "DELETE FROM computer WHERE id = :id";
	public static final String UPDATE = "UPDATE computer SET name= :name, introduced= :introduced,"
			+ " discontinued= :discontinued, company_id = :companyId WHERE id= :id;";

	public static final String AMOUNT = "SELECT COUNT(*) FROM computer;";
	public static final String AMOUNT_SEARCH = "SELECT COUNT(*) FROM computer c LEFT JOIN company b ON c.company_id = b.id WHERE "
			+ "c.name LIKE :search OR b.name LIKE :search;";

	public static final String COMPUTERS_NA = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY computer.name ASC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_ND = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY computer.name DESC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_IA = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY computer.introduced ASC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY computer.introduced DESC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_DA = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY computer.discontinued ASC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_DD = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY computer.discontinued DESC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_CA = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY company.name ASC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_CD = "SELECT computer.id, computer.name, introduced, discontinued, company_id, "
			+ "company.name FROM computer  LEFT JOIN company ON computer.company_id = company.id "
			+ "ORDER BY company.name DESC LIMIT :limit OFFSET :offset;";

	public static final String COMPUTERS_NA_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY computer.name ASC LIMIT :limit OFFSET :offset ;";

	public static final String COMPUTERS_ND_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY computer.name DESC LIMIT :limit OFFSET :offset ;";

	public static final String COMPUTERS_IA_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY computer.introduced ASC LIMIT :limit OFFSET :offset ;";

	public static final String COMPUTERS_ID_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY computer.introduced DESC LIMIT :limit OFFSET :offset ;";

	public static final String COMPUTERS_DA_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY computer.discontinued ASC LIMIT :limit OFFSET :offset ;";

	public static final String COMPUTERS_DD_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY computer.discontinued DESC LIMIT :limit OFFSET :offset ;";

	public static final String COMPUTERS_CA_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LEFT LIKE :search "
			+ "OR company.name LIKE :search ORDER BY company.name ASC LIMIT :limit OFFSET :limit ;";

	public static final String COMPUTERS_CD_SEARCH = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, company.id, company.name  FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE :search "
			+ "OR company.name LIKE :search ORDER BY company.name DESC LIMIT :limit OFFSET :offset ;";
}
