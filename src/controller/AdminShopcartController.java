package controller;

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

import validate.Validate;
import constant.Defines;
import dao.ExhibitionDao;
import dao.ExhibitionDetailDao;
import dao.PayDao;
import dao.ProductDao;
import dao.UserDao;
import entities.Exhibition;
import entities.Pay;
import entities.Product;
import entities.User;

@Controller
@RequestMapping(value = "admincp")
public class AdminShopcartController {
	@Autowired
	private Defines defines;
	@Autowired
	private ExhibitionDao exhibitionDao;
	@Autowired
	private ExhibitionDetailDao exhibitionDetailDao;
	@Autowired
	private PayDao payDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private Validate validate;
	@Autowired
	private ProductDao productDao;
	
	@ModelAttribute
	public void addCommons(ModelMap modelMap, Principal principal) {
		modelMap.addAttribute("defines", defines);
		if(!(principal==null)){
			User user=userDao.getItemByUn(principal.getName());
			if(user.getRole()==1||user.getRole()==2){
				String id_user="-"+user.getId()+"-";
				modelMap.addAttribute("ItemU", user);
				ArrayList<Product> arrayList=(ArrayList<Product>)productDao.getItemBySoluong();
				modelMap.addAttribute("lItemProNum", arrayList);
				modelMap.addAttribute("SumProNum", arrayList.size());
				ArrayList<Exhibition> arrayList2=(ArrayList<Exhibition>) exhibitionDao.getItemsByStatusView(id_user);
				modelMap.addAttribute("sumEx", arrayList2.size());
				modelMap.addAttribute("ItemExNotView", arrayList2);
			}
		}
	}
	//đơn hàng
	@RequestMapping(value = "don-hang", method = RequestMethod.GET)
	public String exhibition(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if(hien!=0){
			rowcount = hien;
		}
		int sum = exhibitionDao.getSum();
		if(sum<rowcount){
			rowcount=sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", exhibitionDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		modelMap.addAttribute("donhang", "donhang");
		return "admincp.shopcart.exhibition";
	}
	
	//đơn hàng
		@RequestMapping(value = "don-hang", method = RequestMethod.POST)
		public String exhibitionP(ModelMap modelMap,Model model,
				@RequestParam(name = "idSeach", defaultValue = "0") String idSeach,
				@RequestParam(name = "p", defaultValue = "1") int page,
				@RequestParam(name = "h", defaultValue = "0") int hien,
				@RequestParam(name="delChoose", defaultValue="0") String[] delChoose,
				HttpServletRequest request) {
			if(!idSeach.equals("0")){
				try {
					long id_ex=Long.parseLong(idSeach);
					if(id_ex<=0){
						model.asMap().clear();
						return "redirect:/admincp/don-hang?msg=idloi";
					}
					int rowcount = defines.ROW_COUNT;
					if(hien!=0){
						rowcount = hien;
					}
					int sum = exhibitionDao.getSum();
					if(sum<rowcount){
						rowcount=sum;
					}
					int sumpage = (int) Math.ceil((float) sum / rowcount);
					modelMap.addAttribute("sumpage", sumpage);
					int offset = (page - 1) * rowcount;
					ArrayList<Exhibition> arrayList=(ArrayList<Exhibition>) exhibitionDao.getItemsByIdEx(offset, rowcount, id_ex);
					modelMap.addAttribute("lItem", arrayList);
					modelMap.addAttribute("page", page);
					modelMap.addAttribute("rowcount", rowcount);
					modelMap.addAttribute("sum", sum);
					modelMap.addAttribute("h", hien);
					modelMap.addAttribute("donhang", "donhang");
					return "admincp.shopcart.exhibition";
				} catch (Exception e) {
					model.asMap().clear();
					return "redirect:/admincp/don-hang?msg=idloi";
				}
			}
			if(delChoose.length>0 && !delChoose[0].equals("0")){
				for (String string : delChoose) {
					long id=Long.parseLong(string);
					exhibitionDao.delItem(id);
					exhibitionDetailDao.delItemsByIdEx(id);
				}
					model.asMap().clear();
				return "redirect:/admincp/don-hang?msg=xoa";
			}else{
				model.asMap().clear();
				return "redirect:/admincp/don-hang";
			}
		}
	
	@RequestMapping(value = "don-hang/chi-tiet/{id}", method = RequestMethod.GET)
	public String exhibitionDetail(ModelMap modelMap, @PathVariable("id") long id, HttpSession session, Principal principal) {
		modelMap.addAttribute("lItem", exhibitionDetailDao.getItems(id));
		modelMap.addAttribute("id_exhibition", id);
		User user=userDao.getItemByUn(principal.getName());
		boolean check=false;
		String iduser="-"+user.getId()+"-";
		String status_view=exhibitionDao.getItemById(id).get(0).getStatus_view();
		String[] strings=status_view.split("-");
		for (String string : strings) {
			if(string.equals(iduser)){
				check=true;
				break;
			}
		}
		if(!check){
			exhibitionDao.editItemBystatus(id,status_view+iduser);
		}
		return "admincp.shopcart.exhibition_detail";
	}
	
	@RequestMapping(value = "don-hang/xoa/{id}", method = RequestMethod.GET)
	public String exhibitionDel(ModelMap modelMap, @PathVariable("id") long id) {
		if ((exhibitionDao.delItem(id) > 0)&&(exhibitionDetailDao.delItemsByIdEx(id)>0)) {
			return "redirect:/admincp/don-hang?msg=xoa";
		}
		return "redirect:/admincp/don-hang?msg=xoa_loi";
	}
	
	//thanh toán
	@RequestMapping(value = "thanh-toan", method = RequestMethod.GET)
	public String pay(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien) {
		int rowcount = defines.ROW_COUNT;
		if(hien!=0){
			rowcount = hien;
		}
		int sum = payDao.getSum();
		if(sum<rowcount){
			rowcount=sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("lItem", payDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		return "admincp.shopcart.pay";
	}
	
	@RequestMapping(value = "thanh-toan/them", method = RequestMethod.GET)
	public String payAdd(ModelMap modelMap) {
		return "admincp.shopcart.pay_add";
	}
	@RequestMapping(value = "thanh-toan/them", method = RequestMethod.POST)
	public String payAdd(@Valid @ModelAttribute("objItem") Pay objItem,
			BindingResult bindingResult,ModelMap modelMap) {
		validate.validatePay(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			return "admincp.shopcart.pay_add";
		}
		if (payDao.addItems(objItem) > 0) {
			return "redirect:/admincp/thanh-toan?msg=them";
		}
		return "redirect:/admincp/thanh-toan?msg=them_loi";
	}
	
	@RequestMapping(value = "thanh-toan/sua/{id}", method = RequestMethod.GET)
	public String payEdit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("Item", payDao.getItem(id));
		return "admincp.shopcart.pay_edit";
	}
	@RequestMapping(value = "thanh-toan/sua/{id}", method = RequestMethod.POST)
	public String payEdit(@Valid @ModelAttribute("objItem") Pay objItem,BindingResult bindingResult,
			ModelMap modelMap, @PathVariable("id") int id) {
		validate.validatePay(objItem, bindingResult);
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("Item", payDao.getItem(id));
			return "admincp.shopcart.pay_edit";
		}
		objItem.setId(id);
		if (payDao.editItem(objItem) > 0) {
			return "redirect:/admincp/thanh-toan?msg=sua";
		}
		return "redirect:/admincp/thanh-toan?msg=sua_loi";
	}
	@RequestMapping(value = "thanh-toan/xoa/{id}", method = RequestMethod.GET)
	public String loaihoaDel(ModelMap modelMap, @PathVariable("id") int id) {
		if (payDao.delItem(id) > 0) {
			return "redirect:/admincp/thanh-toan?msg=xoa";
		}
		return "redirect:/admincp/thanh-toan?msg=xoa_loi";
	}

}
