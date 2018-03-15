package validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import entities.Product;

public class ProductValid implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "e.name",
				"Nhập tên hoa");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "preview", "e.preview",
				"Nhập mô tả");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "number", "e.number",
				"Nhập số lượng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "detail", "e.detail",
				"Nhập chi tiết");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "price", "e.price",
				"Hãy nhập giá bán");
		Product pro = (Product) obj;
		if (pro.getId_type() == 0) {
			e.rejectValue("id_type", "e.id_type", "Hãy chọn loại hoa");
		}
		if (pro.getId_species() == 0) {
			e.rejectValue("id_species", "e.id_species", "Hãy chọn kiểu hoa");
		}
		if (pro.getNumber() < 0) {
			e.rejectValue("number", "e.number", "Số lượng phải lớn hơn hoặc bằng 0");
		}
		if (pro.getPrice() < 0) {
			e.rejectValue("price", "e.price", "Giá phải lớn hơn hoặc bằng 0");
		}

	}

	// public void validate(Object obj, Errors e, int i) {
	// String s = (String) obj;
	// try {
	// Integer.parseInt(s);
	// } catch (NumberFormatException e2) {
	// System.out.println("fdsfdsf");
	// e.rejectValue("id", "negativeValue", new Object[] { 1 },
	// "id can't be negative");
	// }

	// }

}
