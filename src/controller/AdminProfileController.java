package controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import utils.RenameFileLibrary;
import constant.Defines;
import dao.ExhibitionDao;
import dao.ProductDao;
import dao.RoleDao;
import dao.UserDao;
import entities.Exhibition;
import entities.Product;
import entities.User;

@Controller
@RequestMapping(value = "admincp")
public class AdminProfileController {
	@Autowired
	private Defines defines;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ExhibitionDao exhibitionDao;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, Principal principal) {
		modelMap.addAttribute("defines", defines);
		if (!(principal==null)) {
			User user = userDao.getItemByUn(principal.getName());
			if (user.getRole() == 1 || user.getRole() == 2) {
				modelMap.addAttribute("ItemU", user);
				ArrayList<Product> arrayList=(ArrayList<Product>)productDao.getItemBySoluong();
				modelMap.addAttribute("lItemProNum", arrayList);
				modelMap.addAttribute("SumProNum", arrayList.size());
				String id_user="-"+user.getId()+"-";
				ArrayList<Exhibition> arrayList2=(ArrayList<Exhibition>) exhibitionDao.getItemsByStatusView(id_user);
				modelMap.addAttribute("sumEx", arrayList2.size());
				modelMap.addAttribute("ItemExNotView", arrayList2);
			}
		}
	}

	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public String index(ModelMap modelMap, Principal principal,
			@Valid @ModelAttribute("objItem") User objItem,
			BindingResult bindingResult,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,
			HttpServletRequest request) {
		if (!principal.getName().equals("")) {
			User user = userDao.getItemByUn(principal.getName());
			String filename = multipartFile.getOriginalFilename();
			if (!filename.equals("")){
				String picture = user.getPicture();
				if (!picture.equals("notimg.jpg")){
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
			}else{
				objItem.setPicture(user.getPicture());
			}
			if(objItem.getPassword().equals("")){
				objItem.setPassword(user.getPassword());
			}
			objItem.setUsername(user.getUsername());
			objItem.setId(user.getId());
			if(userDao.editItem(objItem)>0){
				return "redirect:/admincp/profile?msg=up";
			}else{
				return "redirect:/admincp/profile?msg=up_loi";
			}
		}
		return "redirect:/admincp/profile?msg=up_loi";
	}

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String indexUp(ModelMap modelMap, Principal principal) {
		User user = new User();
		if (!principal.getName().equals("")) {
			user = userDao.getItemByUn(principal.getName());
			modelMap.addAttribute("User", user);
			modelMap.addAttribute("Role", roleDao.getItems(0, roleDao.getSum()));
			return "admincp.profile.profile";
		}
		return "admincp.profile.profile";
	}

}
