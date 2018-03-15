package controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import utils.Decimalformat;
import utils.RenameFileLibrary;
import utils.SlugUtils;
import constant.Defines;
import dao.AdvertiseDao;
import dao.ExhibitionDao;
import dao.ExhibitionDetailDao;
import dao.ProductDao;
import dao.SpeciesDao;
import dao.TypeDao;
import dao.UserDao;
import entities.Exhibition;
import entities.Product;
import entities.User;

@Controller
@RequestMapping(value = "")
public class PublicUserController {
	@Autowired
	private Decimalformat decimalformat;
	@Autowired
	private Defines defines;
	@Autowired
	private AdvertiseDao advertiseDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SpeciesDao speciesDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ExhibitionDao exhibitionDao;
	@Autowired
	private ExhibitionDetailDao exhibitionDetailDao;
	@Autowired
	private SlugUtils slugUtils;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, HttpSession session,
			Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("decimalformat", decimalformat);
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

	@RequestMapping(value = "profile-pl", method = RequestMethod.GET)
	public String logout_pl(ModelMap modelMap, HttpSession session, Model model) {
		if (session.getAttribute("ItemU") != null) {
			User user = (User) session.getAttribute("ItemU");
			if (user.getRole() == 4) {
				model.asMap().clear();
				return "redirect:/";
			}
			modelMap.addAttribute("title", "Thông tin tài khoản!!!");
			modelMap.addAttribute("ItemU", user);
			return "public.profile_pl";
		}
		model.asMap().clear();
		return "redirect:/";
	}

	@RequestMapping(value = "profile-pl", method = RequestMethod.POST)
	public String login_registerPo(
			@Valid @ModelAttribute("objItem") User objItem,
			BindingResult bindingResult, Model model, HttpSession session,
			ModelMap modelMap,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		if (session.getAttribute("ItemU") != null) {
			User user = (User) session.getAttribute("ItemU");
			int id = user.getId();
			User user1 = userDao.getItem(id);
			objItem.setId(id);
			objItem.setUsername(user1.getUsername());
			if (objItem.getPassword().equals("")) {
				objItem.setPassword(user1.getPassword());
			}
			objItem.setRole(user1.getRole());
			String filename = multipartFile.getOriginalFilename();
			if (!filename.equals("")) {
				// xóa ảnh cũ
				String picture = user1.getPicture();
				if (!picture.equals("notimg.jpg")) {
					final String path = request.getServletContext()
							.getRealPath("/files");
					String url = path + File.separator + picture;
					File delFile = new File(url);
					delFile.delete();
				}
				filename = RenameFileLibrary.renameFile(filename);
				objItem.setPicture(filename);
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
				objItem.setPicture(user1.getPicture());
			}
			if (userDao.editItem(objItem) > 0) {
				model.asMap().clear();
				session.setAttribute("ItemU", objItem);
				return "redirect:/profile-pl?msg=sua";
			}
			model.asMap().clear();
			return "redirect:/profile-pl?msg=sua_loi";
		}
		model.asMap().clear();
		return "redirect:/profile-pl?msg=sua_loi";

	}

	@RequestMapping(value = "don-hang-pl", method = RequestMethod.GET)
	public String donhang_pl(ModelMap modelMap, HttpSession session,
			Model model, @RequestParam(name = "p", defaultValue = "1") int page) {
		if (session.getAttribute("ItemU") != null) {
			User user = (User) session.getAttribute("ItemU");
			if (user.getRole() == 4) {
				model.asMap().clear();
				return "redirect:/";
			}
			int rowcount = defines.ROW_COUNT;
			int sum = exhibitionDao.getSum();
			if (sum < rowcount) {
				rowcount = sum;
			}
			int sumpage = (int) Math.ceil((float) sum / rowcount);
			modelMap.addAttribute("sumpage", sumpage);
			int offset = (page - 1) * rowcount;
			ArrayList<Exhibition> arrayList = (ArrayList<Exhibition>) exhibitionDao
					.getItemsByIdUs(offset, rowcount, user.getId());
			modelMap.addAttribute("ItemEx", arrayList);
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("rowcount", rowcount);
			modelMap.addAttribute("sum", sum);
			modelMap.addAttribute("title", "Đơn hang của bạn của bạn!!!");
			return "public.exhibition";
		}
		model.asMap().clear();
		return "redirect:/dang-nhap-pl";
	}

	@RequestMapping(value = "don-hang-pl/chi-tiet-{id}", method = RequestMethod.GET)
	public String donhangct_pl(ModelMap modelMap, HttpSession session,
			Model model, @PathVariable("id") long id) {
		if (session.getAttribute("ItemU") != null) {
			User user = (User) session.getAttribute("ItemU");
			if (user.getRole() == 4) {
				model.asMap().clear();
				return "redirect:/";
			}
			if (exhibitionDao.checkEx(user.getId(), id).size() > 0) {
				modelMap.addAttribute("lItem", exhibitionDetailDao.getItems(id));
				modelMap.addAttribute("title", "Chi tiết đơn hàng!!!");
				return "public.exhibition_detail";
			} else {
				model.asMap().clear();
				return "redirect:/don-hang-pl";
			}
		}
		model.asMap().clear();
		return "redirect:/";
	}

}
