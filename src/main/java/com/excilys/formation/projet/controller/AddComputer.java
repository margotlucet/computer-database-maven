package com.excilys.formation.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.service.CompanyService;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.validation.ValidationMessage;


@Controller
@RequestMapping("/AddComputer")
public class AddComputer {
	static final Logger LOGGER = LoggerFactory.getLogger(DisplayComputers.class);

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute("computer")
	public Computer getLoginForm(HttpServletRequest request) {
		return new Computer();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request){
		List<Company> listResult = companyService.getListeCompany();
		request.setAttribute("companies", listResult);
		return "addComputer";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("computer") @Valid Computer computer, 
			BindingResult result,RedirectAttributes redirectAttributes, ModelMap model){
		if(result.hasErrors()){
			model.addAttribute("computer", computer);
			return "addComputer";
		}
		else{
			ValidationMessage validation = new ValidationMessage.Builder().build();
			validation.setValid(true);
			validation.setMessage("Computer successfully added");
			computerService.add(computer);
			redirectAttributes.addFlashAttribute("message",validation);
			return "redirect:DisplayComputers";
		}
	}
}
