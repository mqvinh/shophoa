package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import utils.RenameFileLibrary;
import utils.StringLibrary;
import validate.Validate;
import constant.Defines;
import dao.ExhibitionDao;
import dao.ExhibitionDetailDao;
import dao.ProductDao;
import dao.RoleDao;
import dao.UserDao;
import entities.Exhibition;
import entities.ExhibitionDetail;
import entities.Product;
import entities.Role;
import entities.User;

@Controller
@RequestMapping(value = "admincp")
public class AdminUserController {
	@Autowired
	private Defines defines;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private Validate validate;
	@Autowired
	private ExhibitionDao exhibitionDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ExhibitionDetailDao exhibitionDetailDao;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, Principal principal) {
		modelMap.addAttribute("defines", defines);
		if (!(principal == null)) {
			User user = userDao.getItemByUn(principal.getName());
			if (user.getRole() == 1 || user.getRole() == 2) {
				modelMap.addAttribute("ItemU", user);
				ArrayList<Product> arrayList = (ArrayList<Product>) productDao
						.getItemBySoluong();
				modelMap.addAttribute("lItemProNum", arrayList);
				modelMap.addAttribute("SumProNum", arrayList.size());
				String id_user = "-" + user.getId() + "-";
				ArrayList<Exhibition> arrayList2 = (ArrayList<Exhibition>) exhibitionDao
						.getItemsByStatusView(id_user);
				modelMap.addAttribute("sumEx", arrayList2.size());
				modelMap.addAttribute("ItemExNotView", arrayList2);
			}
		}
	}

	// người dùng
	@RequestMapping(value = "nguoi-dung", method = RequestMethod.GET)
	public String index(
			ModelMap modelMap,
			Model model,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien,
			@RequestParam(name = "selectS", defaultValue = "0") String selectS,
			@RequestParam(name = "textSeach", defaultValue = "0") String textSeach) {
		if (!textSeach.equals("0")) {
			if (selectS.equals("1")) {
				try {
					int id = Integer.parseInt(textSeach);
					if (id <= 0) {
						model.asMap().clear();
						return "redirect:/admincp/nguoi-dung?msg=textSeach_id";
					}
					int rowcount = defines.ROW_COUNT;
					if (hien != 0) {
						rowcount = hien;
					}
					int sum = userDao.getSumById(id);
					if (sum < rowcount) {
						rowcount = sum;
					}
					int sumpage = (int) Math.ceil((float) sum / rowcount);
					modelMap.addAttribute("sumpage", sumpage);
					int offset = (page - 1) * rowcount;
					modelMap.addAttribute("lItem",
							userDao.getItems(id, offset, rowcount));
					modelMap.addAttribute("page", page);
					modelMap.addAttribute("rowcount", rowcount);
					modelMap.addAttribute("sum", sum);
					modelMap.addAttribute("h", hien);
					modelMap.addAttribute("nguoidung", "nguoidung");
					modelMap.addAttribute("textSeach", textSeach);
					modelMap.addAttribute("selectS", selectS);
					return "admincp.user.index";
				} catch (Exception e) {
					model.asMap().clear();
					return "redirect:/admincp/nguoi-dung?msg=textSeach_id";
				}
			}
			if (selectS.equals("2")) {
				int rowcount = defines.ROW_COUNT;
				if (hien != 0) {
					rowcount = hien;
				}
				int sum = userDao.getSumByRole(textSeach);
				if (sum < rowcount) {
					rowcount = sum;
				}
				int sumpage = (int) Math.ceil((float) sum / rowcount);
				modelMap.addAttribute("sumpage", sumpage);
				int offset = (page - 1) * rowcount;
				modelMap.addAttribute("lItem",
						userDao.getItems(textSeach, offset, rowcount));
				modelMap.addAttribute("page", page);
				modelMap.addAttribute("rowcount", rowcount);
				modelMap.addAttribute("sum", sum);
				modelMap.addAttribute("h", hien);
				modelMap.addAttribute("nguoidung", "nguoidung");
				modelMap.addAttribute("textSeach", textSeach);
				modelMap.addAttribute("selectS", selectS);
				return "admincp.user.index";
			}
		}
		int rowcount = defines.ROW_COUNT;
		if (hien != 0) {
			rowcount = hien;
		}
		int sum = userDao.getSum();
		if (sum < rowcount) {
			rowcount = sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", userDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		modelMap.addAttribute("nguoidung", "nguoidung");
		return "admincp.user.index";
	}

	@RequestMapping(value = "nguoi-dung", method = RequestMethod.POST)
	public String indexdel(
			ModelMap modelMap,
			Model model,
			@RequestParam(name = "selectS", defaultValue = "0") String selectS,
			@RequestParam(name = "textSeach", defaultValue = "0") String textSeach,
			@RequestParam(name = "delChoose", defaultValue = "0") String[] delChoose,
			HttpServletRequest request,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		if (!textSeach.equals("0")) {
			if (selectS.equals("0")) {
				model.asMap().clear();
				return "redirect:/admincp/nguoi-dung?msg=select_loi";
			}
			else if (selectS.equals("1")) {
				try {
					int id = Integer.parseInt(textSeach);
					if (id <= 0) {
						model.asMap().clear();
						return "redirect:/admincp/nguoi-dung?msg=textSeach_id";
					}
					int rowcount = defines.ROW_COUNT;
					if (hien != 0) {
						rowcount = hien;
					}
					int sum = userDao.getSumById(id);
					if (sum < rowcount) {
						rowcount = sum;
					}
					int sumpage = (int) Math.ceil((float) sum / rowcount);
					modelMap.addAttribute("sumpage", sumpage);
					int offset = (page - 1) * rowcount;
					modelMap.addAttribute("lItem",
							userDao.getItems(id, offset, rowcount));
					modelMap.addAttribute("page", page);
					modelMap.addAttribute("rowcount", rowcount);
					modelMap.addAttribute("sum", sum);
					modelMap.addAttribute("h", hien);
					modelMap.addAttribute("nguoidung", "nguoidung");
					modelMap.addAttribute("textSeach", textSeach);
					modelMap.addAttribute("selectS", selectS);
					return "admincp.user.index";
				} catch (Exception e) {
					model.asMap().clear();
					return "redirect:/admincp/nguoi-dung?msg=textSeach_id";
				}
			}
			else {
				int rowcount = defines.ROW_COUNT;
				if (hien != 0) {
					rowcount = hien;
				}
				int sum = userDao.getSumByRole(textSeach);
				if (sum < rowcount) {
					rowcount = sum;
				}
				int sumpage = (int) Math.ceil((float) sum / rowcount);
				modelMap.addAttribute("sumpage", sumpage);
				int offset = (page - 1) * rowcount;
				modelMap.addAttribute("lItem",
						userDao.getItems(textSeach, offset, rowcount));
				modelMap.addAttribute("page", page);
				modelMap.addAttribute("rowcount", rowcount);
				modelMap.addAttribute("sum", sum);
				modelMap.addAttribute("h", hien);
				modelMap.addAttribute("nguoidung", "nguoidung");
				modelMap.addAttribute("textSeach", textSeach);
				modelMap.addAttribute("selectS", selectS);
				return "admincp.user.index";
			}
		} else {
			if (delChoose.length > 0 && !delChoose[0].equals("0")) {
				for (String i : delChoose) {
					String picture = userDao.getItem(Integer.parseInt(i))
							.getPicture();
					if(userDao.getItem(Integer.parseInt(i)).getRole()!=1){
					if (!picture.equals("notimg.jpg")) {
						final String path = request.getServletContext()
								.getRealPath("/files");
						String url = path + File.separator + picture;
						File delFile = new File(url);
						delFile.delete();
					}
					userDao.delItem(Integer.parseInt(i));
					}
				}
				model.asMap().clear();
				return "redirect:/admincp/nguoi-dung?msg=xoa";
			}
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung?msg=xoa_loi";
		}
	}

	@RequestMapping(value = "nguoi-dung/them", method = RequestMethod.GET)
	public String AddUser(ModelMap modelMap) {
		modelMap.addAttribute("Role", roleDao.getItems(0, roleDao.getSum()));
		return "admincp.user.user_add";
	}

	@RequestMapping(value = "nguoi-dung/them", method = RequestMethod.POST)
	public String AddUser(@Valid @ModelAttribute("objItem") User objItem,
			BindingResult bindingResult, ModelMap modelMap, Model model,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		validate.validateUserAdd(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Role", roleDao.getItems(0, roleDao.getSum()));
			return "admincp.user.user_add";
		}
		if(objItem.getRole()==1){
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung?msg=them_loi_admin";
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
		if (userDao.addItems(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung?msg=them";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung?msg=them_loi";
	}

	@RequestMapping(value = "nguoi-dung/sua/{id}", method = RequestMethod.GET)
	public String EditUser(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Role", roleDao.getItems(0, roleDao.getSum()));
		modelMap.addAttribute("Item", userDao.getItem(id));
		return "admincp.user.user_edit";
	}

	@RequestMapping(value = "nguoi-dung/sua/{id}", method = RequestMethod.POST)
	public String EditUser(@Valid @ModelAttribute("objItem") User objItem,
			BindingResult bindingResult, ModelMap modelMap,
			@PathVariable("id") int id, Model model,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		objItem.setId(id);
		validate.validateUserEdit(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Role", roleDao.getItems(0, roleDao.getSum()));
			modelMap.addAttribute("Item", userDao.getItem(id));
			return "admincp.user.user_edit";
		}
		objItem.setPicture(objItem.getPicture());
		String filename = multipartFile.getOriginalFilename();
		if (!filename.equals("")) {
			// xóa ảnh cũ
			String picture = userDao.getItem(id).getPicture();
			if (!picture.equals("notimg.jpg")) {
				final String path = request.getServletContext().getRealPath(
						"/files");
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
			objItem.setPicture(userDao.getItem(id).getPicture());
		}
		if (!objItem.getPassword().equals("")) {
			objItem.setPassword(StringLibrary.md5(objItem.getPassword()));
		} else {
			objItem.setPassword(userDao.getItem(id).getPassword());
		}
		if (userDao.editItem(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung?msg=sua";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung?msg=sua_loi";
	}

	@RequestMapping(value = "nguoi-dung/xoa/{id}", method = RequestMethod.GET)
	public String productDel(ModelMap modelMap, @PathVariable("id") int id,
			HttpServletRequest request, Model model) {
		String picture = userDao.getItem(id).getPicture();
		if(userDao.getItem(id).getRole()==1){
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung?msg=xoa_loi_admin";
		}
		if (!picture.equals("notimg.jpg")) {
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();
		}
		if (userDao.delItem(id) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung?msg=xoa_loi";
	}

	// cấp bậc
	@RequestMapping(value = "nguoi-dung/cap-bac", method = RequestMethod.GET)
	public String role(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if (hien != 0) {
			rowcount = hien;
		}
		int sum = roleDao.getSum();
		if (sum < rowcount) {
			rowcount = sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", roleDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.user.role";
	}

	@RequestMapping(value = "nguoi-dung/cap-bac", method = RequestMethod.POST)
	public String role(
			ModelMap modelMap,
			HttpServletRequest request,
			Model model,
			@RequestParam(name = "delChoose", defaultValue = "0") String[] delChoose) {
		if (delChoose.length > 0 && !delChoose[0].equals("0")) {
			for (String i : delChoose) {
				roleDao.delItem(Integer.parseInt(i));
				ArrayList<User> arrayList = (ArrayList<User>) userDao
						.getItemByRole(Integer.parseInt(i));
				for (User user : arrayList) {
					String picture = user.getPicture();
					if (!picture.equals("notimg.jpg")) {
						final String path = request.getServletContext()
								.getRealPath("/files");
						String url = path + File.separator + picture;
						File delFile = new File(url);
						delFile.delete();
					}
				}
				userDao.delItemByRole(Integer.parseInt(i));
			}
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung/cap-bac?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung/cap-bac?msg=xoa_loi";
	}

	@RequestMapping(value = "nguoi-dung/cap-bac/them", method = RequestMethod.GET)
	public String roleAdd(ModelMap modelMap) {
		return "admincp.user.role_add";
	}

	@RequestMapping(value = "nguoi-dung/cap-bac/them", method = RequestMethod.POST)
	public String loaihoaAdd(@Valid @ModelAttribute("objItem") Role objItem,
			Model model, BindingResult bindingResult, ModelMap modelMap) {
		validate.validateRole(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			return "admincp.user.role_add";
		}
		if (roleDao.addItems(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung/cap-bac?msg=them";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung/cap-bac?msg=them_loi";
	}

	@RequestMapping(value = "nguoi-dung/cap-bac/sua/{id}", method = RequestMethod.GET)
	public String roleEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", roleDao.getItem(id));
		return "admincp.user.role_edit";
	}

	@RequestMapping(value = "nguoi-dung/cap-bac/sua/{id}", method = RequestMethod.POST)
	public String loaihoaEdit(@Valid @ModelAttribute("objItem") Role objItem,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			@PathVariable("id") int id) {
		objItem.setRole(id);
		validate.validateRole(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Item", roleDao.getItem(id));
			return "admincp.user.role_edit";
		}
		if (roleDao.editItem(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung/cap-bac?msg=sua";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung/cap-bac?msg=sua_loi";
	}

	@RequestMapping(value = "nguoi-dung/cap-bac/xoa/{id}", method = RequestMethod.GET)
	public String loaihoaDel(ModelMap modelMap, @PathVariable("id") int id,
			Model model) {
		if (roleDao.delItem(id) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/nguoi-dung/cap-bac?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/nguoi-dung/cap-bac?msg=xoa_loi";
	}

	@RequestMapping(value = "checkRole", method = RequestMethod.POST)
	public void CheckRole(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("aname");
		int id = Integer.parseInt(request.getParameter("aid"));
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Role> arrayList = (ArrayList<Role>) roleDao.getcheckRole(
				name, id);
		if (arrayList.size() > 0) {
			out.print("1");
		} else {
			out.print("0");
		}
	}

	@RequestMapping(value = "activeUs", method = RequestMethod.POST)
	public void activeUs(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<User> arrayList = (ArrayList<User>) userDao.getActive(id);
		if (arrayList.size() > 0) {
			if (arrayList.get(0).getEnabled() == 0) {
				if (userDao.editItemByEn(id, 1) > 0) {
					out.print("11");
				} else {
					out.print("0");
				}
			} else {
				if (userDao.editItemByEn(id, 0) > 0) {
					out.print("10");
				} else {
					out.print("0");
				}
			}
		} else {
			out.print("0");
		}
	}

	@RequestMapping(value = "activeEx", method = RequestMethod.POST)
	public void activeEx(HttpServletRequest request,
			HttpServletResponse response) {
		long id = Long.parseLong(request.getParameter("id"));
		int check = Integer.parseInt(request.getParameter("check"));
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Exhibition> arrayList = (ArrayList<Exhibition>) exhibitionDao
				.getItemById(id);
		if (arrayList.size() > 0) {
			if (check == 1) {// cập nhật pay
				if (arrayList.get(0).getStatus_pay() == 1) {
					arrayList.get(0).setStatus_pay(0);
					if (exhibitionDao.editItem(arrayList.get(0)) > 0) {

						out.print("110");
					} else {
						out.print("010");
					}
				} else {
					arrayList.get(0).setStatus_pay(1);
					if (exhibitionDao.editItem(arrayList.get(0)) > 0) {

						out.print("111");
					} else {
						out.print("011");
					}
				}
			} else if (check == 2) {// cập nhật kích hoạt
				if (arrayList.get(0).getStatus_active() == 1) {
					arrayList.get(0).setStatus_active(0);
					if (exhibitionDao.editItem(arrayList.get(0)) > 0) {
						out.print("120");
					} else {
						out.print("020");
					}
				} else {
					arrayList.get(0).setStatus_active(1);
					if (exhibitionDao.editItem(arrayList.get(0)) > 0) {
						out.print("121");
					} else {
						out.print("021");
					}
				}
			} else {// cập nhật ship
				if (arrayList.get(0).getStatus_ship() == 1) {
					arrayList.get(0).setStatus_ship(0);
					if (exhibitionDao.editItem(arrayList.get(0)) > 0) {
						ArrayList<ExhibitionDetail> arrayList2 = (ArrayList<ExhibitionDetail>) exhibitionDetailDao
								.getItems(arrayList.get(0).getId());
						for (ExhibitionDetail exhibitionDetail : arrayList2) {
							ArrayList<Product> arrayList3 = (ArrayList<Product>) productDao
									.getItems(exhibitionDetail.getId_product());
							productDao.editItemBySl(
									exhibitionDetail.getId_product(),
									arrayList3.get(0).getNumber()
											+ exhibitionDetail.getNumber(), arrayList3.get(0).getBuy()-exhibitionDetail.getNumber());
						}
						out.print("130");
					} else {
						out.print("030");
					}
				} else {
					arrayList.get(0).setStatus_ship(1);
					if (exhibitionDao.editItem(arrayList.get(0)) > 0) {
						ArrayList<ExhibitionDetail> arrayList2 = (ArrayList<ExhibitionDetail>) exhibitionDetailDao
								.getItems(arrayList.get(0).getId());
						for (ExhibitionDetail exhibitionDetail : arrayList2) {
							ArrayList<Product> arrayList3 = (ArrayList<Product>) productDao
									.getItems(exhibitionDetail.getId_product());
							productDao.editItemBySl(
									exhibitionDetail.getId_product(),
									arrayList3.get(0).getNumber()
											- exhibitionDetail.getNumber(), arrayList3.get(0).getBuy()+exhibitionDetail.getNumber());
						}
						out.print("131");
					} else {
						out.print("031");
					}
				}
			}
		} else {
			out.print("000");
		}
	}

	@RequestMapping(value = "delanh", method = RequestMethod.POST)
	public void delanh(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String picture = request.getParameter("apicture");
		String s = request.getParameter("as");
		int id = Integer.parseInt(request.getParameter("aid"));
		if (s.equals(picture)) {
			if (!picture.equals("notimg.jpg")) {
				String path = request.getServletContext().getRealPath("/files");
				String url = path + File.separator + picture;
				File delFile = new File(url);
				delFile.delete();
				userDao.editItemByImg(id, "notimg.jpg");
			}
			out.print("<img "
					+ "src='"
					+ request.getContextPath()
					+ "/files/notimg.jpg'"
					+ " style='width: 200px;height: 200px' class='img img-thumbnail'>");
		} else {
			out.print("<img "
					+ "src='"
					+ request.getContextPath()
					+ "/files/"
					+ picture
					+ "'"
					+ "style='width: 200px;height: 200px' class='img img-thumbnail'>");
		}
	}

}
