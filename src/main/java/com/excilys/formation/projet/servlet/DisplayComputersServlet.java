package com.excilys.formation.projet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.service.impl.ComputerServiceImpl;
import com.excilys.formation.projet.util.Constant;
import com.excilys.formation.projet.wrapper.PageWrapper;

/**
 * Servlet implementation class DisplayComputersServlet
 */
@WebServlet("/DisplayComputers")
public class DisplayComputersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = LoggerFactory.getLogger(DisplayComputersServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayComputersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = new ComputerServiceImpl();
		
		PageWrapper<ComputerDTO> pageResult = null;
		String page = request.getParameter("page");
		LOGGER.debug("Param page number :"+page);
		String resultsPerPageString = request.getParameter("resultsPerPage");
		LOGGER.debug("Results per page : "+resultsPerPageString);
		String search = request.getParameter("search");	
		int resultsPerPage = 10;
		int currPage = 1;
		
		
		if((page!=null)&&(!page.equals(""))){
			currPage = Integer.parseInt(page);
			LOGGER.debug("Int page number :"+page);
		}
		if((resultsPerPageString!=null)&&(!resultsPerPageString.equals(""))){
			resultsPerPage = Integer.parseInt(resultsPerPageString);
		}
		
		if((search!=null)&&(!search.equals(""))){
			LOGGER.debug("Recherche");
			pageResult = ComputerDTOMapper.toComputerDTOPageWrapper(computerService.getPage(currPage, resultsPerPage, Constant.NAME
					, Constant.ASC, search));
		}
		else{
			pageResult = ComputerDTOMapper.toComputerDTOPageWrapper(computerService.getPage(currPage, resultsPerPage, Constant.NAME
					, Constant.ASC));
		}
	
		request.setAttribute("pageResult", pageResult);
		LOGGER.debug("Ma page : "+pageResult);
		request.setAttribute("search", search);
		request.setAttribute("resultsPerPage", resultsPerPage);
		getServletContext().getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
