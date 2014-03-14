package com.excilys.formation.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.om.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
