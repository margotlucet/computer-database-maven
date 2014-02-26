package com.excilys.formation.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.service.CompanyService;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.validation.ValidationMessage;
import com.excilys.formation.projet.validation.Validator;

@Controller
@RequestMapping("/AddComputer")
public class AddComputer {
	static final Logger LOGGER = LoggerFactory.getLogger(DisplayComputers.class);

	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request){
		List<Company> listResult = companyService.getListeCompany();
		request.setAttribute("companies", listResult);
		return "addComputer";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String post(HttpServletRequest request, RedirectAttributes redirectAttributes){
		ComputerDTO cDTO = new ComputerDTO.Builder().name(request.getParameter("name")).introduced(request.getParameter("introducedDate"))
				.discontinued(request.getParameter("discontinuedDate")).companyId(Long.parseLong(request.getParameter("company"))).build();
		ValidationMessage validation = Validator.validateComputerDTO(cDTO); 
		if(validation.isValid()){
			validation.setMessage("Computer successfully added");
			computerService.add(ComputerDTOMapper.toComputer(cDTO));
			redirectAttributes.addFlashAttribute("message",validation);
			return "redirect:DisplayComputers";
		}
		else{
			request.setAttribute("message",validation);
			request.setAttribute("computer",cDTO);
			return "addComputer";
		}
	}
}
