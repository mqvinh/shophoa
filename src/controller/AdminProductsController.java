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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import utils.RenameFileLibrary;
import validate.ProductValid;
import validate.SpeciesValid;
import validate.TypeValid;
import constant.Defines;
import dao.ExhibitionDao;
import dao.ProductDao;
import dao.SpeciesDao;
import dao.TypeDao;
import dao.UserDao;
import entities.Exhibition;
import entities.Product;
import entities.Species;
import entities.Type;
import entities.User;

@Controller
@RequestMapping(value = "admincp")
public class AdminProductsController {
	@Autowired
	private Defines defines;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SpeciesDao speciesDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductValid productValid;
	@Autowired
	private SpeciesValid speciesValid;
	@Autowired
	private TypeValid typeValid;
	@Autowired
	private ExhibitionDao exhibitionDao;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, Principal principal) {
		modelMap.addAttribute("defines", defines);
		if(!(principal==null)){
			User user=userDao.getItemByUn(principal.getName());
			if(user.getRole()==1||user.getRole()==2){
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
	@ExceptionHandler(NumberFormatException.class) 
	public String handleNumberFormatException(NumberFormatException e) {
		return "chi nhap so";
	}
	// trang hoa
	@RequestMapping(value = "hoa", method = RequestMethod.GET)
	public String index(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if(hien!=0){
			rowcount = hien;
		}
		int sum = productDao.getSum();
		if(sum<rowcount){
			rowcount=sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", productDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.products.products";
	}
	
	@RequestMapping(value = "hoa", method = RequestMethod.POST)
	public String indexDel(ModelMap modelMap,Model model,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien,
			@RequestParam(name="delChoose", defaultValue="0") String[] delChoose,
			HttpServletRequest request) {
		if(delChoose.length>0 && !delChoose[0].equals("0")){
			for (String i : delChoose) {
				String picture = productDao.getItem(Integer.parseInt(i)).getPicture();
				if (!picture.equals("notimg.jpg")) {
				final String path = request.getServletContext().getRealPath("/files");
				String url = path + File.separator + picture;
				File delFile = new File(url);
				delFile.delete();
				}
				productDao.delItemChooseItems(i);
			}
			model.asMap().clear();
			return "redirect:/admincp/hoa?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/hoa";
	}

	@RequestMapping(value = "hoa/them", method = RequestMethod.GET)
	public String productAdd(ModelMap modelMap) {
		modelMap.addAttribute("lSpecies", speciesDao.getItems());
		modelMap.addAttribute("lType", typeDao.getItems());
		return "admincp.products.products_add";
	}

	@RequestMapping(value = "hoa/them", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objItem") Product objItem,
			BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("picturem") CommonsMultipartFile multipartFile,Model model,
			HttpServletRequest request) {
		productValid.validate(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("lSpecies", speciesDao.getItems());
			modelMap.addAttribute("lType", typeDao.getItems());
			return "admincp.products.products_add";
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
			System.out.println("đường dan:"+filepart);
			File file = new File(filepart);
			try {
				multipartFile.transferTo(file);
				System.out.println("upload thanh cong");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				System.out.println("loiiiii");
			}
		}else{
			objItem.setPicture("notimg.jpg");
		}
		objItem.setBuy(0);
		if (productDao.addItems(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/hoa?msg=them";
		}
		return "admincp.hoa.add";
	}

	@RequestMapping(value = "hoa/sua/{id}", method = RequestMethod.GET)
	public String productEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", productDao.getItem(id));
		modelMap.addAttribute("lSpecies", speciesDao.getItems());
		modelMap.addAttribute("lType", typeDao.getItems());
		return "admincp.products.products_edit";
	}
	@RequestMapping(value = "hoa/sua/{id}", method = RequestMethod.POST)
	public String productEdit(@Valid @ModelAttribute("objItem") Product objItem, BindingResult bindingResult,
			ModelMap modelMap, @PathVariable("id") int id,
			@RequestParam("picturem") CommonsMultipartFile multipartFile, Model model,
			HttpServletRequest request) {
		productValid.validate(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("Item", productDao.getItem(id));
			modelMap.addAttribute("lSpecies", speciesDao.getItems());
			modelMap.addAttribute("lType", typeDao.getItems());
			return "admincp.products.products_edit";
		}
		Product product=productDao.getItem(id);
		objItem.setId(id);
		objItem.setPicture(objItem.getPicture());
		String filename = multipartFile.getOriginalFilename();
		if (!filename.equals("")) {
			//xóa ảnh cũ
			String picture = product.getPicture();
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
			objItem.setPicture(product.getPicture());
		}
		objItem.setBuy(product.getBuy());
		if (productDao.editItem(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/hoa?msg=sua";
		}
		model.asMap().clear();
		return "redirect:/admincp/hoa?msg=sua_loi";
	}
	@RequestMapping(value = "hoa/xoa/{id}", method = RequestMethod.GET)
	public String productDel(ModelMap modelMap, @PathVariable("id") int id, HttpServletRequest request,
			Model model) {
		String picture = productDao.getItem(id).getPicture();
		if (!picture.equals("notimg.jpg")) {
		final String path = request.getServletContext().getRealPath("/files");
		String url = path + File.separator + picture;
		File delFile = new File(url);
		delFile.delete();
		}
		if (productDao.delItem(id) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/hoa?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/hoa?msg=xoa_loi";
	}

	//trang kiểu hoa
	@RequestMapping(value = "kieu-hoa", method = RequestMethod.GET)
	public String loaihoa(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if(hien!=0){
			rowcount = hien;
		}
		int sum = speciesDao.getSum();
		if(sum<rowcount){
			rowcount=sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", speciesDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.products.products_species";
	}
	
	@RequestMapping(value = "kieu-hoa", method = RequestMethod.POST)
	public String loaihoaDel(ModelMap modelMap, HttpServletRequest request ,
			@RequestParam(name="delChoose", defaultValue="0") String[] delChoose) {
		if(delChoose.length>0 && !delChoose[0].equals("0")){
			for (String i : delChoose) {
					speciesDao.delItem(Integer.parseInt(i));
					ArrayList<Product> arrayList=(ArrayList<Product>) productDao.getItemBySp(Integer.parseInt(i));
					for (Product product : arrayList) {
						String picture = product.getPicture();
						if (!picture.equals("notimg.jpg")) {
						final String path = request.getServletContext().getRealPath("/files");
						String url = path + File.separator + picture;
						File delFile = new File(url);
						delFile.delete();
						}
					}
					productDao.delItemBySp(Integer.parseInt(i));
				}
			return "redirect:/admincp/kieu-hoa?msg=xoa";
		}
		return "redirect:/admincp/kieu-hoa";
	}

	@RequestMapping(value = "kieu-hoa/them", method = RequestMethod.GET)
	public String loaihoaAdd(ModelMap modelMap) {
		return "admincp.products.products_species_add";
	}
	@RequestMapping(value = "kieu-hoa/them", method = RequestMethod.POST)
	public String loaihoaAdd(@Valid @ModelAttribute("objItem") Species objItem,Model model,
			BindingResult bindingResult,ModelMap modelMap) {
		speciesValid.validate(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			return "admincp.products.products_species_add";
		}
		if (speciesDao.addItems(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/kieu-hoa?msg=them";
		}
		model.asMap().clear();
		return "redirect:/admincp/kieu-hoa?msg=them_loi";
	}

	@RequestMapping(value = "kieu-hoa/sua/{id}", method = RequestMethod.GET)
	public String loaihoaEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", speciesDao.getItem(id));
		return "admincp.products.products_species_edit";
	}
	@RequestMapping(value = "kieu-hoa/sua/{id}", method = RequestMethod.POST)
	public String loaihoaEdit(@Valid @ModelAttribute("objItem") Species objItem,BindingResult bindingResult,Model model,
			ModelMap modelMap, @PathVariable("id") int id) {
		speciesValid.validate(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("Item", speciesDao.getItem(id));
			return "admincp.products.products_species_edit";
		}
		objItem.setId_species(id);
		if (speciesDao.editItem(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/kieu-hoa?msg=sua";
		}
		model.asMap().clear();
		return "redirect:/admincp/kieu-hoa?msg=sua_loi";
	}
	@RequestMapping(value = "kieu-hoa/xoa/{id}", method = RequestMethod.GET)
	public String loaihoaDel(ModelMap modelMap, @PathVariable("id") int id, Model model) {
		if (speciesDao.delItem(id) > 0 || productDao.delItemBySp(id)>0) {
			model.asMap().clear();
			return "redirect:/admincp/kieu-hoa?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/kieu-hoa?msg=xoa_loi";
	}

	// trang loại hoa
	@RequestMapping(value = "loai-hoa", method = RequestMethod.GET)
	public String kieuhoa(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if(hien!=0){
			rowcount = hien;
		}
		int sum = typeDao.getSum();
		if(sum<rowcount){
			rowcount=sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", typeDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.products.products_type";
	}
	
	@RequestMapping(value = "loai-hoa", method = RequestMethod.POST)
	public String kieuhoaDel(ModelMap modelMap, HttpServletRequest request ,Model model,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien,
			@RequestParam(name="delChoose", defaultValue="0") String[] delChoose) {
		if(delChoose.length>0 && !delChoose[0].equals("0")){
			for (String i : delChoose) {
					typeDao.delItem(Integer.parseInt(i));
					ArrayList<Product> arrayList=(ArrayList<Product>) productDao.getItemByTy(Integer.parseInt(i));
					for (Product product : arrayList) {
						String picture = product.getPicture();
						if (!picture.equals("notimg.jpg")) {
						final String path = request.getServletContext().getRealPath("/files");
						String url = path + File.separator + picture;
						File delFile = new File(url);
						delFile.delete();
						}
					}
					productDao.delItemByTy(Integer.parseInt(i));
				}
			model.asMap().clear();
			return "redirect:/admincp/loai-hoa?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/loai-hoa";
	}


	@RequestMapping(value = "loai-hoa/them", method = RequestMethod.GET)
	public String kieuhoaAdd(ModelMap modelMap) {
		return "admincp.products.products_type_add";
	}
	
	@RequestMapping(value = "loai-hoa/them", method = RequestMethod.POST)
	public String kieuhoaAdd(@Valid @ModelAttribute("objItem") Type objItem,Model model,
			BindingResult bindingResult,ModelMap modelMap) {
		typeValid.validate(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			return "admincp.products.products_type_add";
		}
		if (typeDao.addItems(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/loai-hoa?msg=them";
		}
		model.asMap().clear();
		return "redirect:/admincp/loai-hoa?msg=them_loi";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "loai-hoa/sua/{id}", method = RequestMethod.GET)
	public String kieuhoaEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", typeDao.getItem(id));
		return "admincp.products.products_type_edit";
	}
	@RequestMapping(value = "loai-hoa/sua/{id}", method = RequestMethod.POST)
	public String kieuhoaEdit(@Valid @ModelAttribute("objItem") Type objItem,BindingResult bindingResult,Model model,
			ModelMap modelMap, @PathVariable("id") int id) {
		typeValid.validate(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("Item", typeDao.getItem(id));
			return "admincp.products.products_type_edit";
		}
		objItem.setId_type(id);
		if (typeDao.editItem(objItem) > 0) {
			model.asMap().clear();
			return "redirect:/admincp/loai-hoa?msg=sua";
		}
		model.asMap().clear();
		return "redirect:/admincp/loai-hoa?msg=sua_loi";
	}
	@RequestMapping(value = "loai-hoa/xoa/{id}", method = RequestMethod.GET)
	public String kieuhoaDel(ModelMap modelMap, @PathVariable("id") int id, Model model) {
		if (typeDao.delItem(id) > 0 || productDao.delItemByTy(id)>0) {
			model.asMap().clear();
			return "redirect:/admincp/loai-hoa?msg=xoa";
		}
		model.asMap().clear();
		return "redirect:/admincp/loai-hoa?msg=xoa_loi";
	}
	
	
	@RequestMapping(value = "checkSpecies", method = RequestMethod.POST)
	public void CheckSpecies(HttpServletRequest request,
			 HttpServletResponse response) {
		String name=request.getParameter("aname");
		int id= Integer.parseInt(request.getParameter("aid"));
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Species> arrayList=(ArrayList<Species>) speciesDao.getItemCheck(id,name); 
		System.out.println(arrayList.size());
		if (arrayList.size()>0) {
			out.print("1");
		}else{
			out.print("0");
		}
	}
	
	@RequestMapping(value = "checkType", method = RequestMethod.POST)
	public void CheckType(HttpServletRequest request,
			 HttpServletResponse response) {
		String name=request.getParameter("aname");
		int id= Integer.parseInt(request.getParameter("aid"));
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Type> arrayList=(ArrayList<Type>) typeDao.getItemCheck(id,name); 
		System.out.println(arrayList.size());
		if (arrayList.size()>0) {
			out.print("1");
		}else{
			out.print("0");
		}
	}
	
	
	@RequestMapping(value = "delanhPro", method = RequestMethod.POST)
	public void delanhPro(HttpServletRequest request,
			 HttpServletResponse response) {
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String picture=request.getParameter("apicture");
		String s=request.getParameter("as");
		int id=Integer.parseInt(request.getParameter("aid"));
		if(s.equals(picture)){
			if(!picture.equals("notimg.jpg")){
				String path = request.getServletContext().getRealPath("/files");
				String url=path+File.separator+picture;
				File delFile=new File(url);
				delFile.delete();
				productDao.editItemByImg(id,"notimg.jpg");
				}
				out.print("<img "+"src='"+request.getContextPath()+"/files/notimg.jpg'"+
							" style='width: 200px;height: 200px' class='img img-thumbnail'>");
		}else{
			out.print("<img "+"src='"+request.getContextPath()+"/files/"+picture+"'"+
						"style='width: 200px;height: 200px' class='img img-thumbnail'>");
		}
	}


}
