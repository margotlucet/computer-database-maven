package com.excilys.formation.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
@RequestMapping("/EditComputer")
public class EditComputer {
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	
	@ModelAttribute("computer")
	public Computer getLoginForm(HttpServletRequest request) {
		return new Computer();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request,
			 ModelMap model){

		List<Company> listResult = companyService.getListeCompany();
		Computer c = computerService.getById(Long.parseLong(request.getParameter("id")));
		model.addAttribute("computer", c);
		model.addAttribute("companies", listResult);
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(HttpServletRequest request, 
			@ModelAttribute("computer") @Valid Computer computer, 
			BindingResult result,
			RedirectAttributes redirectAttributes,
			ModelMap model){
		if(result.hasErrors()){
			model.addAttribute("computer", computer);
			return "editComputer";
		}
		else{
			ValidationMessage validation = new ValidationMessage.Builder().build();
			validation.setValid(true);
			validation.setMessage("Computer successfully edited");
			computerService.update(computer);
			redirectAttributes.addFlashAttribute("message",validation);
			return "redirect:DisplayComputers";
		}
	}


}
