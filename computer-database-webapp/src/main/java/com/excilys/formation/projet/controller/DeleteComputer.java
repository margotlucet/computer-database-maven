package com.excilys.formation.projet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.validation.ValidationMessage;

@Controller
@RequestMapping("/deletecomputer")
public class DeleteComputer {
	@Autowired
	ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		computerService.delete(Long.parseLong(request.getParameter("id")));
		ValidationMessage validation = new ValidationMessage.Builder()
				.valid(true).message("Computer successfully deleted").build();
		redirectAttributes.addFlashAttribute("message", validation);
		return "redirect:displaycomputers";
	}
}
