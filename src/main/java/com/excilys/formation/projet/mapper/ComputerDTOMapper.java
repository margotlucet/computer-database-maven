package com.excilys.formation.projet.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;

public class ComputerDTOMapper {
	static final Logger LOGGER = LoggerFactory.getLogger(ComputerDTOMapper.class);
	public static ComputerDTO toComputerDTO(Computer c){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String introduced;
		String discontinued;
		if(c.getIntroduced()!=null)
			introduced = formatter.format(c.getIntroduced());
		else
			introduced = Constant.UNKNOWN;
		if(c.getDiscontinued()!=null)
			discontinued = formatter.format(c.getDiscontinued());
		else
			discontinued = Constant.UNKNOWN;

		return new ComputerDTO.Builder().id(c.getId()).name(c.getName()).companyId(c.getCompany().getId())
				.companyName(c.getCompany().getName()).introduced(introduced)
				.discontinued(discontinued).build();
	}

	public static List<ComputerDTO> toComputerDTOList(List<Computer> computers){
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for(Computer c : computers){
			computersDTO.add(toComputerDTO(c));
		}
		return computersDTO;
	}

	public static List<Computer> toComputerList(List<ComputerDTO> computersDTO){
		List<Computer> computers = new ArrayList<Computer>();
		for(ComputerDTO cDTO : computersDTO){
			computers.add(toComputer(cDTO));
		}
		return computers;
	}
	
	public static PageWrapper<ComputerDTO> toComputerDTOPageWrapper(PageWrapper<Computer> pw){
		PageWrapper<ComputerDTO> pwDTO = new PageWrapper<ComputerDTO>();
		pwDTO.setElementList(toComputerDTOList(pw.getElementList()));
		LOGGER.debug("Dans le mapper currPage : "+pw.getCurrPage());
		pwDTO.setCurrPage(pw.getCurrPage());
		pwDTO.setPageCount(pw.getPageCount());
		pwDTO.setResultCount(pw.getResultCount());
		pwDTO.setResultsPerPage(pw.getResultsPerPage());
		return pwDTO;
	}

	public static Computer toComputer(ComputerDTO cDTO){
		Computer c = new Computer.Builder().id(cDTO.getId()).name(cDTO.getName())
				.company(new Company.Builder().id(cDTO.getCompanyId()).name(cDTO.getCompanyName()).build()).build();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date introduced = null;
		Date discontinued = null;
		String introducedString = cDTO.getIntroduced();
		String discontinuedString = cDTO.getDiscontinued();
		if((!introducedString.equals(""))&&(!introducedString.equals(Constant.UNKNOWN))&&(introducedString!=null)){
			try {
				introduced = formatter.parse(cDTO.getIntroduced());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if((!discontinuedString.equals(""))&&(!discontinuedString.equals(Constant.UNKNOWN))&&(discontinuedString!=null)){
			try {
				discontinued = formatter.parse(cDTO.getDiscontinued());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		c.setIntroduced(introduced);
		c.setDiscontinued(discontinued);
		return c;
	}
}
