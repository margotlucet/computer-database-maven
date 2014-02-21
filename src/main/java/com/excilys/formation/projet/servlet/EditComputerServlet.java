package com.excilys.formation.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.service.CompanyService;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.service.impl.CompanyServiceImpl;
import com.excilys.formation.projet.service.impl.ComputerServiceImpl;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComputerService cs = new ComputerServiceImpl();
		CompanyService companyService = new CompanyServiceImpl();
		List<Company> listResult = companyService.getListeCompany();
		Computer c = cs.getById(Long.parseLong(request.getParameter("id")));
		ComputerDTO cDTO = ComputerDTOMapper.toComputerDTO(c);
		request.setAttribute("computer", cDTO);
		request.setAttribute("companies", listResult);
		getServletContext().getRequestDispatcher("/WEB-INF/view/editComputer.jsp").forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComputerDTO cDTO = new ComputerDTO.Builder().id(Long.parseLong(request.getParameter("id"))).name(request.getParameter("name"))
				.introduced(request.getParameter("introducedDate")).discontinued(request.getParameter("discontinuedDate"))
				.companyId(Long.parseLong(request.getParameter("company"))).build();
		Computer c = ComputerDTOMapper.toComputer(cDTO);


		ComputerService computerService = new ComputerServiceImpl();
		//Boolean success = false;
		//StringBuilder message = new StringBuilder();
		computerService.update(c);
		//String companyName = request.getParameter("companyName");
		//if(success){
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);	
		/*
		else{
			request.setAttribute("message",message.toString());
			CompanyService companyService = new CompanyService();
			List<Company> listResult = companyService.getListeCompany();
			request.setAttribute("companies", listResult);
			cDTO.setCompanyName(companyName);
			request.setAttribute("computer", cDTO);
			getServletContext().getRequestDispatcher("/WEB-INF/view/editComputer.jsp").forward(request,response);	
		}
		 */
	}

}
