package controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utils.Decimalformat;
import utils.SlugUtils;
import constant.Defines;
import dao.AdvertiseDao;
import dao.ExhibitionDao;
import dao.ExhibitionDetailDao;
import dao.IntroduceDao;
import dao.PayDao;
import dao.ProductDao;
import dao.RoleDao;
import dao.SlideDao;
import dao.SpeciesDao;
import dao.TypeDao;
import dao.UserDao;
import entities.Exhibition;
import entities.ExhibitionDetail;
import entities.Pay;
import entities.Product;
import entities.Role;
import entities.User;

@Controller
@RequestMapping(value = "gio-hang")
public class PublicShopCartController {
	@Autowired
	private Defines defines;
	@Autowired
	private AdvertiseDao advertiseDao;
	@Autowired
	private SlugUtils slugUtils;
	@Autowired
	private SlideDao slideDao;
	@Autowired
	private SpeciesDao speciesDao;
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private IntroduceDao introduceDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PayDao payDao;
	@Autowired
	private ExhibitionDao exhibitionDao;
	@Autowired
	private ExhibitionDetailDao exhibitionDetailDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private Decimalformat decimalformat;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("decimalformat", decimalformat);
		modelMap.addAttribute("slugU", slugUtils);
		modelMap.addAttribute("ItemAdver", advertiseDao.getItems(0, advertiseDao.getSum()));
		
		if(!(principal==null)){
			User user=userDao.getItemByUn(principal.getName());
				session.setAttribute("ItemU", user);
				modelMap.addAttribute("ItemU", user);
		}
		if(session.getAttribute("ItemU")!=null){
			modelMap.addAttribute("ItemU", session.getAttribute("ItemU"));
		}

		if (session.getAttribute("shopcart") != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList = (ArrayList<Product>) session.getAttribute("shopcart");
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String shopcart(ModelMap modelMap, HttpSession session,@RequestParam(name="msg", defaultValue="") String msg) {
		if(msg.equals("")){
			String[] mangmsg=msg.split("-");
			if(mangmsg.length==2){
				if(mangmsg[0].equals("donhang") && !(mangmsg[1].equals(""))){
					long id_ex=Long.parseLong(mangmsg[1]);
					ArrayList<Exhibition> arrayList=(ArrayList<Exhibition>) exhibitionDao.getItemById(id_ex);
					if(arrayList.size()>0){
						if(arrayList.get(0).getStatus_pay()==0){
							exhibitionDao.editItemBystatusPay(id_ex, 1);
							session.removeAttribute("info");
							session.removeAttribute("ItemPay");
							session.removeAttribute("shopcart");
							User user=(User) session.getAttribute("ItemU");
							if(user.getRole()==4){
								session.removeAttribute("ItemU");
							}
						}
					}
				}
			}
		}
		if (session.getAttribute("shopcart") == null) {
			modelMap.addAttribute("lItemShop", null);
		} else {
			modelMap.addAttribute("lItemShop", session.getAttribute("shopcart"));
		}
		modelMap.addAttribute("title", "Giỏ hàng của bạn!!!");
		return "public.giohang.shopcart";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)//cập nhật
	public String shopcartP(ModelMap modelMap, HttpSession session,Model model,
			@RequestParam("soluong") int[] soluong,
			@RequestParam(name = "xoahang", defaultValue = "0") String[] xoahang) {
		if (session.getAttribute("shopcart") != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList = (ArrayList<Product>) session
					.getAttribute("shopcart");
			ArrayList<String> arrayList2=new ArrayList<>();
			ArrayList<String> arrayList3=new ArrayList<>();
			if (soluong.length > 0) {
				int i = 0;
				for (Product product : arrayList) {
					Product product2=productDao.getItem(product.getId());
					if(soluong[i]>product2.getNumber()){
						arrayList2.add(product2.getName());
					}else if (soluong[i]<0){
						arrayList3.add(product2.getName());
					}else{
						product.setSoluong(soluong[i]);
					}
					i = i + 1;
				}
			}
			if (!xoahang[0].equals("0")) {
				for (String string : xoahang) {
					int id = Integer.parseInt(string);
					for (Product product : arrayList) {
						if (product.getId() == id) {
							arrayList.remove(product);
							break;
						}
					}
				}
			}
			int sum = 0;
			for (Product product : arrayList) {
				sum = sum + product.getSoluong() * product.getPrice();
			}
			if(arrayList2.size()>0){
				modelMap.addAttribute("Hoaerror_max", arrayList2);
			}
			if(arrayList3.size()>0){
				modelMap.addAttribute("Hoaerror_min", arrayList3);
			}
			modelMap.addAttribute("sumProShop", arrayList.size());
			modelMap.addAttribute("sumMoney", sum);
			session.setAttribute("shopcart", arrayList);
			modelMap.addAttribute("lItemShop", session.getAttribute("shopcart"));
		}
		modelMap.addAttribute("title", "Giỏ hàng của bạn!!!");
		return "public.giohang.shopcart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "them", method = RequestMethod.POST)//thêm
	public String shopcartAdd(ModelMap modelMap, HttpSession session,
			@ModelAttribute("soluong") int soluong,
			@ModelAttribute("id") int id) {
		ArrayList<Product> ArProduct = new ArrayList<>();
		Product pro = new Product();
		pro = productDao.getItemFull(id);
		if (session.getAttribute("shopcart") != null) {
			boolean check = false;
			ArProduct = (ArrayList<Product>) session.getAttribute("shopcart");
			for (Product product : ArProduct) {
				if (product.getId() == id) {
					check = true;
					product.setSoluong(product.getSoluong() + soluong);
					break;
				}
			}
			if (!check) {
				pro.setSoluong(soluong);
				ArProduct.add(pro);
			}
			session.setAttribute("shopcart", ArProduct);
			return "redirect:/gio-hang";
		} else {
			pro.setSoluong(soluong);
			ArProduct.add(pro);
			session.setAttribute("shopcart", ArProduct);
			return "redirect:/gio-hang";
		}
	}
	
	@RequestMapping(value = "thong-tin", method = RequestMethod.GET)
	public String shopcart_infor(ModelMap modelMap, HttpSession session, Model model) {
		if (session.getAttribute("ItemU") == null) {
			model.asMap().clear();
			return "redirect:/dang-nhap-pl";
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList=(ArrayList<Product>) session.getAttribute("shopcart");
			if(arrayList==null || arrayList.size() <=0){
				model.asMap().clear();
				return "redirect:/gio-hang?msg=giohang";
			}else{
			modelMap.addAttribute("lItemPay", payDao.getItems(0, payDao.getSum()));
			modelMap.addAttribute("title", "Thông tin khách hàng!!!");
			return "public.giohang.shopcart_infor";
			}
		}
	}
	
	@RequestMapping(value = "xac-nhan", method = RequestMethod.GET)
	public String shopcart_accessg(ModelMap modelMap, HttpSession session, Model model) {
		if (session.getAttribute("ItemU") == null) {
			model.asMap().clear();
			return "redirect:/dang-nhap-pl";
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList=(ArrayList<Product>) session.getAttribute("shopcart");
			if(arrayList==null || arrayList.size() <=0){
				model.asMap().clear();
				return "redirect:/gio-hang?msg=giohang";
			}else{
				modelMap.addAttribute("infor", session.getAttribute("info"));
				modelMap.addAttribute("ItemPay", session.getAttribute("Itempay"));
				modelMap.addAttribute("lItemShop", session.getAttribute("shopcart"));
				modelMap.addAttribute("title", "Xác nhận thông tin!!!");
				return "public.giohang.shopcart_access";
			}
		}
	}
	
	@RequestMapping(value = "xac-nhan", method = RequestMethod.POST)
	public String shopcart_access(ModelMap modelMap, HttpSession session, Model model,
			@RequestParam(name="id_pay", defaultValue="0") int id_pay,
			@RequestParam("info") String info) {
		if (session.getAttribute("ItemU") == null) {
			model.asMap().clear();
			return "redirect:/dang-nhap-pl";
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList=(ArrayList<Product>) session.getAttribute("shopcart");
			if(arrayList==null || arrayList.size() <=0){
				model.asMap().clear();
				return "redirect:/gio-hang?msg=giohang";
			}else{
			if(id_pay==0){
				model.asMap().clear();
				return "redirect:/gio-hang/thong-tin?msg=error_pay";
			}else{
				session.setAttribute("info", info);
				modelMap.addAttribute("infor", info);
				session.setAttribute("ItemPay", payDao.getItem(id_pay));
				modelMap.addAttribute("ItemPay", session.getAttribute("Itempay"));
				modelMap.addAttribute("lItemShop", session.getAttribute("shopcart"));
				modelMap.addAttribute("title", "Xác nhận thông tin!!!");
				return "public.giohang.shopcart_access";
			}
			}
		}
	}
	
	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST)
	public String shopcart_pay(ModelMap modelMap, HttpSession session, Model model, HttpServletRequest request) {
		if (session.getAttribute("ItemU") == null) {
			model.asMap().clear();
			return "redirect:/dang-ky-pl";
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList1=(ArrayList<Product>) session.getAttribute("shopcart");
			if(arrayList1==null || arrayList1.size() <=0){
				model.asMap().clear();
				return "redirect:/gio-hang?msg=giohang";
			}
			String info=(String) session.getAttribute("info");
			User user=(User) session.getAttribute("ItemU");
			Role role= new Role();
			if(user.getRole()==4){
				role=(Role) roleDao.getItem(4);
			}else{
				User user2=(User) userDao.getItemByUn(user.getUsername());
				role=(Role) roleDao.getItem(user2.getRole());
			}
			Pay pay=(Pay) session.getAttribute("ItemPay");
			@SuppressWarnings("unchecked")
			ArrayList<Product>  lproduct=(ArrayList<Product>) session.getAttribute("shopcart");
			
			Exhibition exhibition=new Exhibition();
			long id_ex= (long)System.currentTimeMillis();
			ArrayList<Exhibition> arrayList=(ArrayList<Exhibition>) exhibitionDao.getItemById(id_ex);
			while(true){
				if(arrayList.size()>0){
					id_ex=(int) System.nanoTime();
					arrayList=(ArrayList<Exhibition>) exhibitionDao.getItemById(id_ex);
				}else{
					break;
				}
			}
			exhibition.setId(id_ex);
			exhibition.setId_user(user.getId()); 
			exhibition.setName_role(role.getName());
			exhibition.setName_user(user.getFullname());
			exhibition.setStatus_active(0);
			exhibition.setStatus_pay(0);
			exhibition.setStatus_ship(0);
			exhibition.setId_pay(pay.getId());
			exhibition.setName_pay(pay.getName());
			exhibition.setMore_infor(info);
			exhibition.setAddress_user(user.getAddress());
			int tongtien=0;
			if(exhibitionDao.addItem(exhibition)>0){
				ExhibitionDetail exhibitionDetail=new ExhibitionDetail();
				exhibitionDetail.setId_exhibition(id_ex);
				for (Product product : lproduct) {
					exhibitionDetail.setId_product(product.getId());
					exhibitionDetail.setName_product(product.getName());
					exhibitionDetail.setPrice_product(product.getPrice());
					exhibitionDetail.setNumber(product.getSoluong());
					tongtien=tongtien+product.getPrice()*product.getSoluong();
					if(exhibitionDetailDao.addItem(exhibitionDetail)>0){
						Product product2=productDao.getItem(product.getId());
						product2.setBuy(product2.getBuy()+product.getSoluong());
						if(product2.getNumber()-product.getSoluong()<0){
							exhibitionDetailDao.delItemsByIdEx(id_ex);
							exhibitionDao.delItem(id_ex);
							model.asMap().clear();
							return "redirect:/gio-hang?msg=donhang_loi";
						}
//						product2.setNumber(product2.getNumber()-product.getSoluong());
//						productDao.editItem(product2);
					}else{
						exhibitionDao.delItem(id_ex);
						return "redirect:/gio-hang?msg=donhang_loi";
					}
				}
//				session.removeAttribute("info");
//				session.removeAttribute("ItemPay");
//				session.removeAttribute("shopcart");
//				if(user.getRole()==4){
//					session.removeAttribute("ItemU");
//				}
				model.asMap().clear();
				if(pay.getId()==1){
					return "redirect:https://www.nganluong.vn/button_payment.php?receiver=maiquangvinhi4@gmail.com&product_name="+id_ex+"&price="+tongtien+"&return_url="+request.getContextPath()+"/gio-hang?msg=donhang-"+id_ex+"&comments=";
				}
				session.removeAttribute("info");
				session.removeAttribute("ItemPay");
				session.removeAttribute("shopcart");
				if(user.getRole()==4){
					session.removeAttribute("ItemU");
				}
				return "redirect:/gio-hang?msg=donhang";
			}else{
				return "redirect:/gio-hang?msg=donhang_loi";
			}
		}
	}

}
