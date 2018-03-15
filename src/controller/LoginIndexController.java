package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import utils.RenameFileLibrary;
import utils.SlugUtils;
import utils.StringLibrary;
import validate.Validate;
import constant.Defines;
import dao.AdvertiseDao;
import dao.CountDao;
import dao.ProductDao;
import dao.SpeciesDao;
import dao.TypeDao;
import dao.UserDao;
import entities.Count;
import entities.Product;
import entities.User;

@Controller
@RequestMapping(value = "")
public class LoginIndexController {
	@Autowired
	private Defines defines;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SpeciesDao speciesDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SlugUtils slugUtils;
	@Autowired
	private AdvertiseDao advertiseDao;
	@Autowired
	private CountDao countDao;
	@Autowired
	private Validate validate;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, HttpSession session,
			Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("slugU", slugUtils);
		modelMap.addAttribute("ItemAdver", advertiseDao.getItems(0, advertiseDao.getSum()));
		if (!(principal == null)) {
			User user = userDao.getItemByUn(principal.getName());
			session.setAttribute("ItemU", user);
			modelMap.addAttribute("ItemU", user);
		}
		if (session.getAttribute("ItemU") != null) {
			modelMap.addAttribute("ItemU", session.getAttribute("ItemU"));
		}

		if (session.getAttribute("shopcart") != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList = (ArrayList<Product>) session
					.getAttribute("shopcart");
			int sum = 0;
			for (Product product : arrayList) {
				sum = sum + product.getSoluong() * product.getPrice();
			}
			modelMap.addAttribute("sumProShop", arrayList.size());
			modelMap.addAttribute("sumMoney", sum);
		} else {
			modelMap.addAttribute("sumProShop", 0);
			modelMap.addAttribute("sumMoney", 0);
		}

		int sumS = speciesDao.getSum();
		modelMap.addAttribute("lNSpecies", speciesDao.getItemsNum(0, sumS));
		modelMap.addAttribute("lSpecies", speciesDao.getItems(0, sumS));

		int sumP = typeDao.getSum();
		modelMap.addAttribute("lNType", typeDao.getItemsNum(0, sumP));
		modelMap.addAttribute("lType", typeDao.getItems(0, sumP));

		int sumPro = productDao.getSum();
		modelMap.addAttribute("sumPro", sumPro);
	}

	@RequestMapping(value = "dang-nhap-ql", method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		return "admincp.login";
	}

	@RequestMapping(value = "dang-nhap-pl", method = RequestMethod.GET)
	public String login_check(ModelMap modelMap) {
		modelMap.addAttribute("title", "Đăng nhập!!!");
		return "public.login";
	}

	@RequestMapping(value = "thongtin-mua", method = RequestMethod.GET)
	public String userInfo(ModelMap modelMap) {
		modelMap.addAttribute("title", "Thông tin mua hàng!!!");
		return "public.user_info";
	}

	@RequestMapping(value = "thongtin-mua", method = RequestMethod.POST)
	public String userInfoPost(ModelMap modelMap, Model model,
			HttpSession session, @RequestParam("fullname") String fullname,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("address") String address) {
		User user = new User();
		user.setFullname(fullname);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		user.setRole(4);
		user.setId(0);
		session.setAttribute("ItemU", user);
		// modelMap.addAttribute("lItemPay", payDao.getItems(0,
		// payDao.getSum()));
		model.asMap().clear();
		return "redirect:/gio-hang/thong-tin";
	}

	@RequestMapping(value = "dang-nhap-pl", method = RequestMethod.POST)
	public String login_check(
			ModelMap modelMap,
			Model model,
			@RequestParam(name = "choose", defaultValue = "") String choose,
			@RequestParam(name = "username", defaultValue = "") String username,
			@RequestParam(name = "password", defaultValue = "") String password,
			HttpSession session) {
		if (choose.equals("2")) {
			model.asMap().clear();
			return "redirect:/dang-ky-pl";
		}
		if (choose.equals("1")) {
			if (session.getAttribute("shopcart") == null) {
				model.asMap().clear();
				return "redirect:/gio-hang?msg=giohang";
			} else {
				model.asMap().clear();
				return "redirect:/thongtin-mua";
			}
		}
		if (!username.equals("") && !password.equals("")) {
			password = StringLibrary.md5(password);
			ArrayList<User> arrayList = (ArrayList<User>) userDao
					.getItemByUnPw(username, password);
			if (arrayList.size() > 0) {
				session.setAttribute("ItemU", arrayList.get(0));
				Count count=(Count)countDao.getItem(1);
				if(count.getCount()>=0){
					count.setCount(count.getCount()+1);
					countDao.editItem(count);
				}
				model.asMap().clear();
				return "redirect:/";
			} else {
				model.asMap().clear();
				return "redirect:/dang-nhap-pl?msg=error";
			}
		}
		return "public.login";
	}

	@RequestMapping(value = "dang-ky-pl", method = RequestMethod.GET)
	public String login_register(ModelMap modelMap) {
		modelMap.addAttribute("title", "Đăng ký tài khoản!!!");
		return "public.register";
	}

	@RequestMapping(value = "dang-ky-pl", method = RequestMethod.POST)
	public String login_registerPo(
			@Valid @ModelAttribute("objItem") User objItem,
			BindingResult bindingResult, Model model,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		objItem.setRole(3);
		validate.validateUserAdd(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			model.asMap().clear();
			return "redirect:/dang-ky-pl";
		}
		String filename = multipartFile.getOriginalFilename();
		if (!filename.equals("")) {
			filename = RenameFileLibrary.renameFile(filename);
			objItem.setPicture(filename);
			String dirpart = request.getServletContext().getRealPath("files");
			File dir = new File(dirpart);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String filepart = request.getServletContext().getRealPath(
					"files" + File.separator + filename);
			System.out.println(filepart);
			File file = new File(filepart);
			try {
				multipartFile.transferTo(file);
				System.out.println("upload thanh cong");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				System.out.println("loiiiii");
			}
		} else {
			objItem.setPicture("notimg.jpg");
		}
		objItem.setEnabled(1);
		objItem.setPassword(StringLibrary.md5(objItem.getPassword()));
		if (userDao.addItems(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/dang-nhap-pl?msg=them";
		}
		model.asMap().clear();
		return "redirect:/dang-nhap-pl?msg=them_loi";
	}

	@RequestMapping(value = "dang-xuat-pl", method = RequestMethod.GET)
	public String logout_pl(ModelMap modelMap, HttpSession session,
			Model model, Principal principal) {
		if (session.getAttribute("ItemU") != null) {
			session.removeAttribute("ItemU");
			Count count=(Count)countDao.getItem(1);
			if(count.getCount()>=0){
				count.setCount(count.getCount()-1);
				countDao.editItem(count);
			}
			modelMap.addAttribute("ItemU", null);
		}
		if (!(principal == null)) {
			return "redirect:/logout";
		}
		model.asMap().clear();
		return "redirect:/";
	}
	

	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	public void CheckUser(HttpServletRequest request,
			 HttpServletResponse response) {
		String name=request.getParameter("aname");
		int id= Integer.parseInt(request.getParameter("aid"));
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<User> arrayList=(ArrayList<User>) userDao.getcheckUS(name,id); 
		if (arrayList.size()>0) {
			out.print("1");
		}else{
			out.print("0");
		}
	}
	@RequestMapping(value = "checkEmail", method = RequestMethod.POST)
	public void CheckEmailr(HttpServletRequest request,
			 HttpServletResponse response) {
		String email=request.getParameter("aemail");
		int id= Integer.parseInt(request.getParameter("aid"));
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<User> arrayList=(ArrayList<User>) userDao.getcheckEm(email,id); 
		if (arrayList.size()>0) {
			out.print("1");
		}else{
			out.print("0");
		}
	}

}
