package com.excilys.formation.projet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.om.Log;
import com.excilys.formation.projet.repository.ComputerRepository;
import com.excilys.formation.projet.repository.LogRepository;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.wrapper.PageWrapper;

@Service
public class ComputerServiceImpl implements ComputerService {
	static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerServiceImpl.class);
	@Autowired
	private ComputerRepository computerRepository;
	@Autowired
	private LogRepository logRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#getComputerById(
	 * long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Computer getById(long id) {
		return computerRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#addComputer(com.
	 * excilys.formation.projet.om.Computer)
	 */
	@Override
	@Transactional
	public void add(Computer c) {
		Log l = new Log();
		if (c.getCompany().getId() == 0)
			c.setCompany(null);
		c = computerRepository.save(c);
		l.setComputerId(c.getId());
		l.setDescription("New computer Id : " + c.toString());
		LOGGER.debug("Log to add : " + l);
		logRepository.save(l);
		LOGGER.debug("Exiting add");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#editComputer(com
	 * .excilys.formation.projet.om.Computer)
	 */
	@Override
	@Transactional
	public void update(Computer c) {
		Log l = new Log();
		if (c.getCompany().getId() == 0)
			c.setCompany(null);
		c = computerRepository.save(c);
		l.setComputerId(c.getId());
		l.setDescription("Computer updated : " + c.toString());
		logRepository.save(l);
		LOGGER.debug("Exiting update");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.formation.projet.service.ComputerService#deleteComputer(java
	 * .lang.String)
	 */
	@Override
	@Transactional
	public void delete(long id) {
		Log l = new Log();
		computerRepository.delete(id);
		Computer c = new Computer.Builder().id(id).build();
		l.setComputerId(c.getId());
		l.setDescription("Computer with id " + id + " deleted");
		logRepository.save(l);
		LOGGER.debug("Exiting delete");
	}

	@Override
	@Transactional(readOnly = true)
	public PageWrapper<Computer> getPage(int page, int nbResult,
			String orderBy, String orderDirection) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		pw.setCurrPage(page);
		pw.setResultsPerPage(nbResult);
		PageRequest pageRequest = new PageRequest(page - 1, nbResult,
				Sort.Direction.ASC, orderBy);
		pw.setElementList(computerRepository.findAll(pageRequest).getContent());
		int resultCount = (int) computerRepository.count();
		LOGGER.debug("Result Count : " + resultCount);
		pw.setResultCount(resultCount);
		if (resultCount % nbResult != 0)
			pw.setPageCount(((int) resultCount / nbResult) + 1);
		else
			pw.setPageCount((int) resultCount / nbResult);
		LOGGER.debug("Page Wrapper : " + pw);
		return pw;

	}

	@Override
	@Transactional(readOnly = true)
	public PageWrapper<Computer> getPage(int page, int nbResult,
			String orderBy, String orderDirection, String search) {
		PageWrapper<Computer> pw = new PageWrapper<Computer>();
		pw.setCurrPage(page);
		pw.setResultsPerPage(nbResult);
		PageRequest pageRequest = new PageRequest(page - 1, nbResult,
				Sort.Direction.ASC, orderBy);
		pw.setElementList(computerRepository.findByCompanyNameLikeOrNameLike(
				"%" + search + "%", "%" + search + "%", pageRequest));
		int resultCount = computerRepository.countByCompanyNameLikeOrNameLike(
				"%" + search + "%", "%" + search + "%").intValue();
		pw.setResultCount(resultCount);
		if (resultCount % nbResult != 0)
			pw.setPageCount(((int) resultCount / nbResult) + 1);
		else
			pw.setPageCount((int) resultCount / nbResult);
		return pw;

	}

}
