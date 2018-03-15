package validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import entities.Type;

public class TypeValid implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Type.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "e.name",
				"Không được để trống hoặc chỉ nhập cách");

	}
	public void validatePay(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "e.name",
				"Không được để trống hoặc chỉ nhập cách");

	}

}
