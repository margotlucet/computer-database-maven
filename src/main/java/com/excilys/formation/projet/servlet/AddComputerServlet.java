package com.excilys.formation.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.service.CompanyService;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.service.impl.CompanyServiceImpl;
import com.excilys.formation.projet.service.impl.ComputerServiceImpl;
import com.excilys.formation.projet.validation.*;
/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyService companyService = new CompanyServiceImpl();
		List<Company> listResult = companyService.getListeCompany();
		request.setAttribute("companies", listResult);
		getServletContext().getRequestDispatcher("/WEB-INF/view/addComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO cDTO = new ComputerDTO.Builder().name(request.getParameter("name")).introduced(request.getParameter("introducedDate"))
				.discontinued(request.getParameter("discontinuedDate")).companyId(Long.parseLong(request.getParameter("company"))).build();
		ValidationMessage validation = Validator.validateComputerDTO(cDTO); 
		if(validation.isValid()){
			ComputerService computerService = new ComputerServiceImpl();
			computerService.add(ComputerDTOMapper.toComputer(cDTO));
			request.setAttribute("message", validation);
			System.out.println(validation);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);	
		}
		else{
			request.setAttribute("message",validation);
			request.setAttribute("computer",cDTO);
			getServletContext().getRequestDispatcher("/WEB-INF/view/addComputer.jsp").forward(request,response);	
		}
	}

}
