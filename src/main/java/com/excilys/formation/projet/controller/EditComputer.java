package com.excilys.formation.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.service.CompanyService;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.validation.ValidationMessage;
import com.excilys.formation.projet.validation.Validator;

@Controller
@RequestMapping("/EditComputer")
public class EditComputer {
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request){
		List<Company> listResult = companyService.getListeCompany();
		Computer c = computerService.getById(Long.parseLong(request.getParameter("id")));
		ComputerDTO cDTO = ComputerDTOMapper.toComputerDTO(c);
		request.setAttribute("computer", cDTO);
		request.setAttribute("companies", listResult);
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(HttpServletRequest request, RedirectAttributes redirectAttributes){
		ComputerDTO cDTO = new ComputerDTO.Builder().id(Long.parseLong(request.getParameter("id"))).name(request.getParameter("name"))
				.introduced(request.getParameter("introducedDate")).discontinued(request.getParameter("discontinuedDate"))
				.companyId(Long.parseLong(request.getParameter("company"))).build();
		ValidationMessage validation = Validator.validateComputerDTO(cDTO); 
		if(validation.isValid()){
			validation.setMessage("Computer successfully edited");
			Computer c = ComputerDTOMapper.toComputer(cDTO);
			computerService.update(c);
			//redirectAttributes.addAttribute("message",validation);
			redirectAttributes.addFlashAttribute("message",validation);
			return "redirect:DisplayComputers";	
		}
		else{
			request.setAttribute("message",validation);
			request.setAttribute("computer",cDTO);
			return "editComputer";
		}
	}


}
