package com.excilys.formation.projet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.om.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public List<Company> findAll();

}
