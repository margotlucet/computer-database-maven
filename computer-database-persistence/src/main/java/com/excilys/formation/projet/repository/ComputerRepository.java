package com.excilys.formation.projet.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.om.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
	List<Computer> findByCompanyNameLikeOrNameLike(String companyName,
			String name, Pageable page);

	Long countByCompanyNameLikeOrNameLike(String companyName, String name);
}
