package com.excilys.formation.projet.validation;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.formation.projet.om.Computer;

public class ComputerValidator implements Validator {

	@Override
	public boolean supports(Class<?> classA) {
		return Computer.class.equals(classA);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		Computer c = (Computer) o;
		Locale locale = LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		switch (language) {
		case "en":
			break;
		case "fr":
			break;
		default:
			break;
		}
	}

}
