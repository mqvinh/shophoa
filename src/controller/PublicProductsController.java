package controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utils.Decimalformat;
import utils.SlugUtils;
import constant.Defines;
import dao.AdvertiseDao;
import dao.ProductDao;
import dao.SpeciesDao;
import dao.TypeDao;
import dao.UserDao;
import entities.Product;
import entities.Species;
import entities.Type;
import entities.User;

@Controller
@RequestMapping(value = "")
public class PublicProductsController {
	@Autowired
	private Defines defines;
	@Autowired
	private Decimalformat decimalformat;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SpeciesDao speciesDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SlugUtils slugUtils;
	@Autowired
	private AdvertiseDao advertiseDao;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, HttpSession session,
			Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("decimalformat", decimalformat);
		modelMap.addAttribute("slugU", slugUtils);
		modelMap.addAttribute("ItemAdver",
				advertiseDao.getItems(0, advertiseDao.getSum()));

		if (!(principal == null)) {
			User user = userDao.getItemByUn(principal.getName());
			session.setAttribute("ItemU", user);
			modelMap.addAttribute("ItemU", user);
		}
		if (session.getAttribute("ItemU") != null) {
			modelMap.addAttribute("ItemU", session.getAttribute("ItemU"));
		}

		String chuoiId = "--";
		if (session.getAttribute("shopcart") != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList = (ArrayList<Product>) session
					.getAttribute("shopcart");
			int sum = 0;
			for (Product product : arrayList) {
				sum = sum + product.getSoluong() * product.getPrice();
				chuoiId = chuoiId + "-" + product.getId() + "-";
			}
			modelMap.addAttribute("sumProShop", arrayList.size());
			modelMap.addAttribute("sumMoney", sum);
			modelMap.addAttribute("chuoiId", chuoiId);
		} else {
			modelMap.addAttribute("sumProShop", 0);
			modelMap.addAttribute("sumMoney", 0);
			modelMap.addAttribute("chuoiId", chuoiId);
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

	// danh mục
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/{slug}-{lid}-{pid}", method = RequestMethod.GET)
	public String product(ModelMap modelMap, @PathVariable("lid") int lid,
			@PathVariable("pid") int pid,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@PathVariable("slug") String slug,
			@RequestParam(name = "h", defaultValue = "0") int hien, Model model) {
		int rowcount = 6;
		if (hien != 0) {
			rowcount = hien;
		}
		if (lid == 2) {
			if (slugUtils.makeSlug(speciesDao.getItem(pid).getName()).equals(
					slug)) {
				int sum = productDao.getSumBySp(pid);
				if (sum < rowcount) {
					rowcount = sum;
				}
				int sumpage = (int) Math.ceil((float) sum / rowcount);
				modelMap.addAttribute("sumpage", sumpage);
				int offset = (page - 1) * rowcount;
				Species sp = speciesDao.getItem(pid);
				modelMap.addAttribute("CName", sp.getName());
				modelMap.addAttribute("CId", sp.getId_species());
				modelMap.addAttribute("lItem",
						productDao.getItemsBySp(pid, offset, rowcount));
				modelMap.addAttribute("page", page);
				modelMap.addAttribute("rowcount", rowcount);
				modelMap.addAttribute("sum", sum);
				modelMap.addAttribute("h", hien);
				modelMap.addAttribute("lid", lid);
				modelMap.addAttribute("title", sp.getName());
			} else {
				model.asMap().clear();
				return "redirect:/"
						+ slugUtils.makeSlug(speciesDao.getItem(pid).getName())
						+ "-" + lid + "-" + pid;
			}
		} else if (lid == 1) {
			if (slugUtils.makeSlug(typeDao.getItem(pid).getName()).equals(slug)) {
				int sum = productDao.getSumTy(pid);
				if (sum < rowcount) {
					rowcount = sum;
				}
				int sumpage = (int) Math.ceil((float) sum / rowcount);
				modelMap.addAttribute("sumpage", sumpage);
				int offset = (page - 1) * rowcount;
				Type Ty = typeDao.getItem(pid);
				modelMap.addAttribute("CName", Ty.getName());
				modelMap.addAttribute("CId", Ty.getId_type());
				modelMap.addAttribute("lItem",
						productDao.getItemsByTy(pid, offset, rowcount));
				modelMap.addAttribute("page", page);
				modelMap.addAttribute("rowcount", rowcount);
				modelMap.addAttribute("sum", sum);
				modelMap.addAttribute("lid", lid);
				modelMap.addAttribute("title", Ty.getName());
			} else {
				model.asMap().clear();
				return "redirect:/"
						+ slugUtils.makeSlug(typeDao.getItem(pid).getName())
						+ "-" + lid + "-" + pid;
			}
		}
		return "public.shophoa.product";
	}

	// chi tiết
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/{slug}-{lid}-{pid}/{slugm}-{id}", method = RequestMethod.GET)
	public String product_detail(ModelMap modelMap, @PathVariable("id") int id,
			@PathVariable("lid") int lid, @PathVariable("slug") String slug,
			@PathVariable("slugm") String slugm, @PathVariable("pid") int pid,
			Model model) {
		Product pr = productDao.getItem(id);
		modelMap.addAttribute("Item", pr);
		if (lid == 1) {
			if (slugUtils.makeSlug(typeDao.getItem(pid).getName()).equals(slug)
					&& slugUtils.makeSlug(productDao.getItem(id).getName())
							.equals(slugm)) {
				modelMap.addAttribute("ItemC", typeDao.getItem(pr.getId_type()));
				modelMap.addAttribute("lItemLq",
						productDao.getItemsByTy(pr.getId_type(), 0, 4));
			} else {
				model.asMap().clear();
				return "redirect:/"
						+ slugUtils.makeSlug(typeDao.getItem(pid).getName())
						+ "-" + lid + "-" + pid + "/"
						+ slugUtils.makeSlug(productDao.getItem(id).getName())
						+ "-" + id;
			}
		}
		if (lid == 2) {
			if (slugUtils.makeSlug(speciesDao.getItem(pid).getName()).equals(
					slug)
					&& slugUtils.makeSlug(productDao.getItem(id).getName())
							.equals(slugm)) {
				modelMap.addAttribute("ItemC",
						speciesDao.getItem(pr.getId_species()));
				modelMap.addAttribute("lItemLq",
						productDao.getItemsBySp(pr.getId_species(), 0, 4));
			} else {
				model.asMap().clear();
				return "redirect:/"
						+ slugUtils.makeSlug(speciesDao.getItem(pid).getName())
						+ "-" + lid + "-" + pid + "/"
						+ slugUtils.makeSlug(productDao.getItem(id).getName())
						+ "-" + id;
			}
		}
		modelMap.addAttribute("title", pr.getName());
		modelMap.addAttribute("lid", lid);
		modelMap.addAttribute("pid", pid);
		return "public.shophoa.product_detail";
	}

	@RequestMapping(value = "/seach", method = RequestMethod.GET)
	public String product_seach(ModelMap modelMap,
			@RequestParam( name="lid", defaultValue="1") int lid, 
			@RequestParam(name = "p", defaultValue = "1") int page,
			@ModelAttribute(name="textSeach") String textSeach,
			@RequestParam(name = "h", defaultValue = "0") int hien, Model model) {
		if(!textSeach.equals("")){
			try {
				textSeach=new String (textSeach.getBytes("iso-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		int rowcount = 6;
		if (hien != 0) {
			rowcount = hien;
		}
			ArrayList<Product> arrayList=(ArrayList<Product>)productDao.getItemsSeachName(textSeach);
			int sum = arrayList.size();
			if (sum < rowcount) {
				rowcount = sum;
			}
			int sumpage = (int) Math.ceil((float) sum / rowcount);
			modelMap.addAttribute("sumpage", sumpage);
			int offset = (page - 1) * rowcount;
			ArrayList<Product> arrayList2=(ArrayList<Product>)productDao.getItemsSeachName(textSeach,offset, rowcount); 
			for (Product product : arrayList2) {
				product.setName(product.getName().replace(textSeach, "<span style='background: yellow'>"+textSeach+"</span>"));
			}
			modelMap.addAttribute("lProductByname",arrayList2);
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("rowcount", rowcount);
			modelMap.addAttribute("sum", sum);
			modelMap.addAttribute("lid", lid);
			modelMap.addAttribute("title", "Tìm kiếm "+textSeach);
			modelMap.addAttribute("textSeach", textSeach);
		return "public.shophoa.product_seach";
		}else{
			model.asMap().clear();
			return "redirect:/";
		}
	}
	@RequestMapping(value = "/seach", method = RequestMethod.POST)
	public String product_seachP(ModelMap modelMap,
			@RequestParam("lid") int lid, 
			@RequestParam(name = "p", defaultValue = "1") int page,
			@ModelAttribute("textSeach") String textSeach,
			@RequestParam(name = "h", defaultValue = "0") int hien, Model model) {
		if(!textSeach.equals("")){
		int rowcount = 6;
		if (hien != 0) {
			rowcount = hien;
		}
			ArrayList<Product> arrayList=(ArrayList<Product>)productDao.getItemsSeachName(textSeach);
			int sum = arrayList.size();
			if (sum < rowcount) {
				rowcount = sum;
			}
			int sumpage = (int) Math.ceil((float) sum / rowcount);
			modelMap.addAttribute("sumpage", sumpage);
			int offset = (page - 1) * rowcount;
			ArrayList<Product> arrayList2=(ArrayList<Product>)productDao.getItemsSeachName(textSeach,offset, rowcount); 
			for (Product product : arrayList2) {
				product.setName(product.getName().replace(textSeach, "<span style='background: yellow'>"+textSeach+"</span>"));
			}
			modelMap.addAttribute("lProductByname",arrayList2);
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("rowcount", rowcount);
			modelMap.addAttribute("sum", sum);
			modelMap.addAttribute("lid", lid);
			modelMap.addAttribute("title", "Tìm kiếm "+textSeach);
			modelMap.addAttribute("textSeach", textSeach);
		return "public.shophoa.product_seach";
		}else{
			model.asMap().clear();
			return "redirect:/";
		}
	}
}
