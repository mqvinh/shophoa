package controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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
import validate.Validate;
import constant.Defines;
import dao.AdvertiseDao;
import dao.ContactDao;
import dao.ExhibitionDao;
import dao.IntroduceDao;
import dao.ProductDao;
import dao.SlideDao;
import dao.UserDao;
import entities.Advertise;
import entities.Exhibition;
import entities.Introduce;
import entities.Product;
import entities.Slide;
import entities.User;

@Controller
@RequestMapping(value = "admincp")
public class AdminOtherController {
	@Autowired
	private Defines defines;
	@Autowired
	private IntroduceDao introduceDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private AdvertiseDao advertiseDao;
	@Autowired
	private SlideDao slideDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private Validate validate;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ExhibitionDao exhibitionDao;

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

	// trang giới thiệu
	@RequestMapping(value = "gioi-thieu", method = RequestMethod.GET)
	public String introduce(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if (hien != 0) {
			rowcount = hien;
		}
		int sum = introduceDao.getSum();
		if (sum < rowcount) {
			rowcount = sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", introduceDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.other.introduce";
	}

	@RequestMapping(value = "gioi-thieu/them", method = RequestMethod.GET)
	public String introduceAdd(ModelMap modelMap) {
		return "admincp.other.introduce_add";
	}

	@RequestMapping(value = "gioi-thieu/them", method = RequestMethod.POST)
	public String introduceAdd(
			@Valid @ModelAttribute("objItem") Introduce objItem,
			BindingResult bindingResult,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		validate.validateIntroduce(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			return "admincp.other.introduce_add";
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
		if (introduceDao.addItems(objItem) > 0) {
			return "redirect:/admincp/gioi-thieu?msg=them";
		}
		return "redirect:/admincp/gioi-thieu?msg=them_loi";
	}

	@RequestMapping(value = "gioi-thieu/sua/{id}", method = RequestMethod.GET)
	public String introduceEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", introduceDao.getItem(id));
		return "admincp.other.introduce_edit";
	}

	@RequestMapping(value = "gioi-thieu/sua/{id}", method = RequestMethod.POST)
	public String introduceEdit(
			@Valid @ModelAttribute("objItem") Introduce objItem,
			BindingResult bindingResult, ModelMap modelMap,
			@PathVariable("id") int id,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		objItem.setId(id);
		validate.validateIntroduce(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Item", introduceDao.getItem(id));
			return "admincp.other.introduce_edit";
		}
		objItem.setPicture(objItem.getPicture());
		String filename = multipartFile.getOriginalFilename();
		if (!filename.equals("")) {
			// xóa ảnh cũ
			String picture = introduceDao.getItem(id).getPicture();
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();

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
			objItem.setPicture(introduceDao.getItem(id).getPicture());
		}
		if (introduceDao.editItem(objItem) > 0) {
			return "redirect:/admincp/gioi-thieu?msg=sua";
		}
		return "redirect:/admincp/gioi-thieu?msg=sua_loi";
	}

	@RequestMapping(value = "gioi-thieu/xoa/{id}", method = RequestMethod.GET)
	public String productDel(ModelMap modelMap, @PathVariable("id") int id,
			HttpServletRequest request) {
		String picture = introduceDao.getItem(id).getPicture();
		if (!picture.equals("notimg.jpg")) {
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();
		}
		if (introduceDao.delItem(id) > 0) {
			return "redirect:/admincp/gioi-thieu?msg=xoa";
		}
		return "redirect:/admincp/gioi-thieu?msg=xoa_loi";
	}

	// liên hệ
	@RequestMapping(value = "lien-he", method = RequestMethod.GET)
	public String contact(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if (hien != 0) {
			rowcount = hien;
		}
		int sum = contactDao.getSum();
		if (sum < rowcount) {
			rowcount = sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", contactDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.other.contact";
	}

	@RequestMapping(value = "lien-he", method = RequestMethod.POST)
	public String contact(
			ModelMap modelMap,
			Model model,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien,
			@RequestParam(name = "delChoose", defaultValue = "0") String[] delChoose) {
		if (delChoose.length > 0 && !delChoose[0].equals("0")) {
			for (String i : delChoose) {
				contactDao.delItem(Integer.parseInt(i));
			}
			model.asMap().clear();
			return "redirect:/admincp/lien-he?msg=xoa";
		} else {
			model.asMap().clear();
			return "redirect:/admincp/lien-he?msg=xoa_loi";
		}
	}

	@RequestMapping(value = "lien-he/chi-tiet/{id}", method = RequestMethod.GET)
	public String contactDetail(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", contactDao.getItem(id));
		return "admincp.other.contact_detail";
	}

	@RequestMapping(value = "lien-he/xoa/{id}", method = RequestMethod.GET)
	public String loaihoaDel(ModelMap modelMap, @PathVariable("id") int id,
			Model model) {
		if (contactDao.delItem(id) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/lien-he?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/lien-he?msg=xoa_loi";
	}

	// trang quảng cáo
	// @PreAuthorize("")
	@RequestMapping(value = "quang-cao", method = RequestMethod.GET)
	public String advertise(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if (hien != 0) {
			rowcount = hien;
		}
		int sum = advertiseDao.getSum();
		if (sum < rowcount) {
			rowcount = sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", advertiseDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.other.advertise";
	}
	
	@RequestMapping(value = "quang-cao", method = RequestMethod.POST)
	public String advertise(ModelMap modelMap,
			@RequestParam(name="delChoose", defaultValue="0") String[] delChoose,
			HttpServletRequest request, Model model) {
		if(delChoose.length>0 && !delChoose[0].equals("0")){
			for (String i : delChoose) {
				String picture = advertiseDao.getItem(Integer.parseInt(i)).getPicture();
				if (!picture.equals("notimg.jpg")) {
				final String path = request.getServletContext().getRealPath("/files");
				String url = path + File.separator + picture;
				File delFile = new File(url);
				delFile.delete();
				}
				advertiseDao.delItem(Integer.parseInt(i));
			}
			model.asMap().clear();
			return "redirect:/admincp/quang-cao?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/quang-cao?msg=xoa_loi";
	}

	@RequestMapping(value = "quang-cao/them", method = RequestMethod.GET)
	public String advertiseAdd(ModelMap modelMap) {
		return "admincp.other.advertise_add";
	}

	@RequestMapping(value = "quang-cao/them", method = RequestMethod.POST)
	public String advertiseAdd(
			@Valid @ModelAttribute("objItem") Advertise objItem,
			BindingResult bindingResult,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		validate.validateAdvertise(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			return "admincp.other.advertise_add";
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
		if (advertiseDao.addItems(objItem) > 0) {
			return "redirect:/admincp/quang-cao?msg=them";
		}
		return "redirect:/admincp/quang-cao?msg=them_loi";
	}

	@RequestMapping(value = "quang-cao/sua/{id}", method = RequestMethod.GET)
	public String advertiseEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", advertiseDao.getItem(id));
		return "admincp.other.advertise_edit";
	}

	@RequestMapping(value = "quang-cao/sua/{id}", method = RequestMethod.POST)
	public String advertiseEdit(
			@Valid @ModelAttribute("objItem") Advertise objItem,
			BindingResult bindingResult, ModelMap modelMap,
			@PathVariable("id") int id,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		objItem.setId(id);
		validate.validateAdvertise(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Item", advertiseDao.getItem(id));
			return "admincp.other.advertise_edit";
		}
		objItem.setPicture(objItem.getPicture());
		String filename = multipartFile.getOriginalFilename();
		if (!filename.equals("")) {
			// xóa ảnh cũ
			String picture = advertiseDao.getItem(id).getPicture();
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();

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
			objItem.setPicture(advertiseDao.getItem(id).getPicture());
		}
		if (advertiseDao.editItem(objItem) > 0) {
			return "redirect:/admincp/quang-cao?msg=sua";
		}
		return "redirect:/admincp/quang-cao?msg=sua_loi";
	}

	@RequestMapping(value = "quang-cao/xoa/{id}", method = RequestMethod.GET)
	public String advertiseDel(ModelMap modelMap, @PathVariable("id") int id,
			HttpServletRequest request) {
		String picture = advertiseDao.getItem(id).getPicture();
		if (!picture.equals("notimg.jpg")) {
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();
		}
		if (advertiseDao.delItem(id) > 0) {
			return "redirect:/admincp/quang-cao?msg=xoa";
		}
		return "redirect:/admincp/quang-cao?msg=xoa_loi";
	}

//	@RequestMapping(value = "hop-thu", method = RequestMethod.GET)
//	public String inbox(ModelMap modelMap) {
//		return "admincp.other.inbox";
//	}
//
//	@RequestMapping(value = "hop-thu/gui", method = RequestMethod.GET)
//	public String inboxDetail(ModelMap modelMap) {
//		return "admincp.other.inbox_detail";
//	}

	// slide
	@RequestMapping(value = "slide", method = RequestMethod.GET)
	public String slide(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if (hien != 0) {
			rowcount = hien;
		}
		int sum = slideDao.getSum();
		if (sum < rowcount) {
			rowcount = sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", slideDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.other.slide";
	}
	
	@RequestMapping(value = "slide", method = RequestMethod.POST)
	public String slide(ModelMap modelMap,Model model,
			@RequestParam(name="delChoose", defaultValue="0") String[] delChoose,
			HttpServletRequest request) {
		if(delChoose.length>0 && !delChoose[0].equals("0")){
			for (String i : delChoose) {
				String picture = slideDao.getItem(Integer.parseInt(i)).getPicture();
				if (!picture.equals("notimg.jpg")) {
				final String path = request.getServletContext().getRealPath("/files");
				String url = path + File.separator + picture;
				File delFile = new File(url);
				delFile.delete();
				}
				slideDao.delItem(Integer.parseInt(i));
			}
			model.asMap().clear();
			return "redirect:/admincp/slide?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/slide?msg=xoa_loi";
	}

	@RequestMapping(value = "slide/them", method = RequestMethod.GET)
	public String slideAdd(ModelMap modelMap) {
		return "admincp.other.slide_add";
	}

	@RequestMapping(value = "slide/them", method = RequestMethod.POST)
	public String slideAdd(@Valid @ModelAttribute("objItem") Slide objItem,
			BindingResult bindingResult,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		validate.validateSlide(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			return "admincp.other.slide_add";
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
		if (slideDao.addItems(objItem) > 0) {
			return "redirect:/admincp/slide?msg=them";
		}
		return "redirect:/admincp/slide?msg=them_loi";
	}

	@RequestMapping(value = "slide/sua/{id}", method = RequestMethod.GET)
	public String slideEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", slideDao.getItem(id));
		return "admincp.other.slide_edit";
	}

	@RequestMapping(value = "slide/sua/{id}", method = RequestMethod.POST)
	public String sideEdit(@Valid @ModelAttribute("objItem") Slide objItem,
			BindingResult bindingResult, ModelMap modelMap,
			@PathVariable("id") int id,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		objItem.setId(id);
		validate.validateSlide(objItem, bindingResult);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Item", slideDao.getItem(id));
			return "admincp.other.slide_edit";
		}
		objItem.setPicture(objItem.getPicture());
		String filename = multipartFile.getOriginalFilename();
		if (!filename.equals("")) {
			// xóa ảnh cũ
			String picture = slideDao.getItem(id).getPicture();
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();

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
			objItem.setPicture(slideDao.getItem(id).getPicture());
		}
		if (slideDao.editItem(objItem) > 0) {
			return "redirect:/admincp/slide?msg=sua";
		}
		return "redirect:/admincp/slide?msg=sua_loi";
	}

	@RequestMapping(value = "slide/xoa/{id}", method = RequestMethod.GET)
	public String slideDel(ModelMap modelMap, @PathVariable("id") int id,
			HttpServletRequest request) {
		String picture = slideDao.getItem(id).getPicture();
		if (!picture.equals("notimg.jpg")) {
			final String path = request.getServletContext().getRealPath(
					"/files");
			String url = path + File.separator + picture;
			File delFile = new File(url);
			delFile.delete();
		}
		if (slideDao.delItem(id) > 0) {
			return "redirect:/admincp/slide?msg=xoa";
		}
		return "redirect:/admincp/slide?msg=xoa_loi";
	}

}
