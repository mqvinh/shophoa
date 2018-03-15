package validate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import dao.RoleDao;
import dao.UserDao;
import entities.Product;
import entities.Role;
import entities.User;

public class Validate implements Validator {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "e.name",
		// "Nhập tên hoa");
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "preview", "e.preview",
		// "Nhập mô tả");
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "number", "e.number",
		// "Nhập số lượng");
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "detail", "e.detail",
		// "Nhập chi tiết");
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "price", "e.price",
		// "Hãy nhập giá bán");
		// Product pro = (Product) obj;
		// if (pro.getId_type() == 0) {
		// e.rejectValue("id_type", "e.id_type", "Hãy chọn loại hoa");
		// }
		// if (pro.getId_species() == 0) {
		// e.rejectValue("id_species", "e.id_species", "Hãy chọn kiểu hoa");
		// }
		// if (pro.getNumber() < 0) {
		// e.rejectValue("number", "e.number",
		// "Số lượng phải lớn hơn hoặc bằng 0");
		// }
		// if (pro.getPrice() < 0) {
		// e.rejectValue("price", "e.price", "Giá phải lớn hơn hoặc bằng 0");
		// }

	}

	public void validatePay(Object obj, Errors e) {
		ValidationUtils
				.rejectIfEmptyOrWhitespace(e, "name", "e.name",
						"Tên phương thức thanh toán khoogn được để trống hoặc chỉ nhập khoản trắng");
	}

	public void validateUserAdd(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "e.username",
				"Tên đăng nhập được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "e.password",
				"Password không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "role", "e.role",
				"Hãy chọn cấp bậc");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fullname", "e.fullname",
				"Fullname không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "address", "e.address",
				"Address không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "e.email",
				"Email không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "phone", "e.phone",
				"Phone không được để trống hoặc chỉ nhập khoản trắng");

		User user = (User) obj;
		if (!(user.getPassword().equals(user.getRpassword()))) {
			e.rejectValue("rpassword", "e.rpassword",
					"Mật khẩu lặp lại không khớp");
		}
		if (user.getRole() == 0) {
			e.rejectValue("role", "e.role", "Hãy chọn cấp bậc");
		}
		ArrayList<User> arrayList = (ArrayList<User>) userDao.getcheckUS(
				user.getUsername(), 0);
		if (arrayList.size() > 0) {
			e.rejectValue("username", "e.username", "Username đã tồn tại");
		}
		ArrayList<User> arrayList1 = (ArrayList<User>) userDao.getcheckEm(
				user.getEmail(), 0);
		if (arrayList1.size() > 0) {
			e.rejectValue("email", "e.email", "email đã được đăng ký");
		}
	}

	public void validateUserEdit(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "e.username",
				"Tên đăng nhập được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "role", "e.role",
				"Hãy chọn cấp bậc");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fullname", "e.fullname",
				"Fullname không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "address", "e.address",
				"Address không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "e.email",
				"Email không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "phone", "e.phone",
				"Phone không được để trống hoặc chỉ nhập khoản trắng");

		User user = (User) obj;
		if (!(user.getPassword().equals(user.getRpassword()))) {
			e.rejectValue("rpassword", "e.rpassword",
					"Mật khẩu lặp lại không khớp");
		}
		if (user.getRole() == 0) {
			e.rejectValue("role", "e.role", "Hãy chọn cấp bậc");
		}
		ArrayList<User> arrayList = (ArrayList<User>) userDao.getcheckUS(
				user.getUsername(), user.getId());
		if (arrayList.size() > 0) {
			e.rejectValue("username", "e.username", "Username đã tồn tại");
		}
		ArrayList<User> arrayList1 = (ArrayList<User>) userDao.getcheckEm(
				user.getEmail(), user.getId());
		if (arrayList1.size() > 0) {
			e.rejectValue("email", "e.email", "email đã được đăng ký");
		}
	}

	public void validateRole(Object obj, Errors e) {
		ValidationUtils
				.rejectIfEmptyOrWhitespace(e, "name", "e.name",
						"Tên tên cấp bậc không được để trống hoặc chỉ nhập khoản trắng");

		Role role = (Role) obj;
		ArrayList<Role> arrayList = new ArrayList<>();
		if (role.getRole() != 0) {
			arrayList = (ArrayList<Role>) roleDao.getcheckRole(role.getName(),
					role.getRole());
		} else {
			arrayList = (ArrayList<Role>) roleDao.getcheckRole(role.getName(),
					0);
		}
		if (arrayList.size() > 0) {
			e.rejectValue("name", "e.name", "Cấp bậc đã tồn tại");
		}
	}

	public void validateIntroduce(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "e.name",
				"Tên không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "preview", "e.preview",
				"Mô tả không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "detail", "e.detail",
				"Chi tiết không được để trống hoặc chỉ nhập khoản trắng");

	}

	public void validateContact(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fullname", "e.fullname",
				"Tên không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "e.email",
				"Email không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "phone", "e.phone",
				"Số điện thoại không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "preview", "e.preview",
				"Mô tả không được để trống hoặc chỉ nhập khoản trắng");

	}
	
	public void validateAdvertise(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "link", "e.link",
				"Link không được để trống hoặc chỉ nhập khoản trắng");

	}
	
	public void validateSlide(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "preview", "e.preview",
				"Mô tả không được để trống hoặc chỉ nhập khoản trắng");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "link", "e.link",
				"Link không được để trống hoặc chỉ nhập khoản trắng");

	}

}
