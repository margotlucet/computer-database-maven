package com.excilys.formation.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.projet.om.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String Username);
}
