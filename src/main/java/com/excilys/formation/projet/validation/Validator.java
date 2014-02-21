package com.excilys.formation.projet.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.util.Constant;

public class Validator {

	public static ValidationMessage validateComputerDTO(ComputerDTO cDTO){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		boolean valid = false;
		String message = "";
		if(!(cDTO.getName().equals(""))&&(cDTO!=null)){
			valid = true;
			message = Constant.OK;
		}
		else{
			message = Constant.EMPTY_NAME;
		}
		try {
			formatter.parse(cDTO.getIntroduced());
			formatter.parse(cDTO.getDiscontinued());
		} catch (ParseException e) {
			e.printStackTrace();
			valid = false;
			if(message.equals(Constant.EMPTY_NAME))
				message = Constant.WRONG_DATE_EMPTY_NAME;
			else
				message = Constant.WRONG_DATE;
			return new ValidationMessage.Builder().message(message).valid(valid).build();
		}
		return new ValidationMessage.Builder().message(message).valid(valid).build();
	}
}
