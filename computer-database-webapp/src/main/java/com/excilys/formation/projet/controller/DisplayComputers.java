package com.excilys.formation.projet.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;

@Controller
@RequestMapping("/displaycomputers")
public class DisplayComputers {
	static final Logger LOGGER = LoggerFactory
			.getLogger(DisplayComputers.class);

	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request, ModelMap model) {

		PageWrapper<ComputerDTO> pageResult = null;
		String page = request.getParameter("page");
		LOGGER.debug("Param page number :" + page);
		String resultsPerPageString = request.getParameter("resultsPerPage");
		LOGGER.debug("Results per page : " + resultsPerPageString);
		LOGGER.debug("Results per page : " + resultsPerPageString);
		String search = request.getParameter("search");
		int resultsPerPage = 10;
		int currPage = 1;

		if ((page != null) && (!page.equals(""))) {
			currPage = Integer.parseInt(page);
			LOGGER.debug("Int page number :" + page);
		}
		if ((resultsPerPageString != null)
				&& (!resultsPerPageString.equals(""))) {
			resultsPerPage = Integer.parseInt(resultsPerPageString);
		}

		if ((search != null) && (!search.equals(""))) {
			LOGGER.debug("Recherche");
			pageResult = ComputerDTOMapper
					.toComputerDTOPageWrapper(computerService
							.getPage(currPage, resultsPerPage, Constant.NAME,
									Constant.ASC, search));
		} else {
			PageWrapper<Computer> pageWrapper = computerService.getPage(
					currPage, resultsPerPage, Constant.NAME, Constant.ASC);
			LOGGER.debug("pageWrapper : " + pageWrapper);
			pageResult = ComputerDTOMapper
					.toComputerDTOPageWrapper(pageWrapper);

			/*
			 * pageResult = ComputerDTOMapper
			 * .toComputerDTOPageWrapper(computerService.getPage(currPage,
			 * resultsPerPage, Constant.NAME, Constant.ASC));
			 */
		}
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("search", search);
		model.addAttribute("resultsPerPage", resultsPerPage);
		return "dashboard";
	}
}
