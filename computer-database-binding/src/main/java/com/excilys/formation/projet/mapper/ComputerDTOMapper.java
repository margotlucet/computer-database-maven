package com.excilys.formation.projet.mapper;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;

public class ComputerDTOMapper {
	static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDTOMapper.class);

	public static ComputerDTO toComputerDTO(Computer c) {
		String introduced;
		String discontinued;
		if (c.getIntroduced() != null)
			introduced = c.getIntroduced().toString("dd/MM/yyy");
		else
			introduced = Constant.UNKNOWN;
		if (c.getDiscontinued() != null)
			discontinued = c.getDiscontinued().toString("dd/MM/yyy");
		else
			discontinued = Constant.UNKNOWN;

		return new ComputerDTO.Builder().id(c.getId()).name(c.getName())
				.companyId(c.getCompany().getId())
				.companyName(c.getCompany().getName()).introduced(introduced)
				.discontinued(discontinued).build();
	}

	public static List<ComputerDTO> toComputerDTOList(List<Computer> computers) {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			computersDTO.add(toComputerDTO(c));
		}
		return computersDTO;
	}

	public static List<Computer> toComputerList(List<ComputerDTO> computersDTO) {
		List<Computer> computers = new ArrayList<Computer>();
		for (ComputerDTO cDTO : computersDTO) {
			computers.add(toComputer(cDTO));
		}
		return computers;
	}

	public static PageWrapper<ComputerDTO> toComputerDTOPageWrapper(
			PageWrapper<Computer> pw) {
		PageWrapper<ComputerDTO> pwDTO = new PageWrapper<ComputerDTO>();
		pwDTO.setElementList(toComputerDTOList(pw.getElementList()));
		LOGGER.debug("Dans le mapper currPage : " + pw.getCurrPage());
		pwDTO.setCurrPage(pw.getCurrPage());
		pwDTO.setPageCount(pw.getPageCount());
		pwDTO.setResultCount(pw.getResultCount());
		pwDTO.setResultsPerPage(pw.getResultsPerPage());
		return pwDTO;
	}

	public static Computer toComputer(ComputerDTO cDTO) {
		Computer c = new Computer.Builder()
				.id(cDTO.getId())
				.name(cDTO.getName())
				.company(
						new Company.Builder().id(cDTO.getCompanyId())
								.name(cDTO.getCompanyName()).build()).build();
		DateTime introduced = null;
		DateTime discontinued = null;
		String introducedString = cDTO.getIntroduced();
		String discontinuedString = cDTO.getDiscontinued();
		LOGGER.debug("Introduced : " + introducedString);
		LOGGER.debug("Discontinued: " + discontinuedString);
		if ((!introducedString.equals(""))
				&& (!introducedString.equals(Constant.UNKNOWN))
				&& (introducedString != null)) {
			introduced = DateTime.parse(cDTO.getIntroduced(),
					DateTimeFormat.forPattern("DD/MM/yyyy"));
		}
		if ((!discontinuedString.equals(""))
				&& (!discontinuedString.equals(Constant.UNKNOWN))
				&& (discontinuedString != null)) {
			discontinued = DateTime.parse(cDTO.getDiscontinued(),
					DateTimeFormat.forPattern("DD/MM/yyyy"));
		}
		c.setIntroduced(introduced);
		c.setDiscontinued(discontinued);
		return c;
	}
}
