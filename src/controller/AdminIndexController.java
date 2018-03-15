package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utils.Decimalformat;
import constant.Defines;
import dao.ContactDao;
import dao.CountDao;
import dao.ExhibitionDao;
import dao.ExhibitionDetailDao;
import dao.ProductDao;
import dao.RoleDao;
import dao.UserDao;
import entities.Count;
import entities.Exhibition;
import entities.ExhibitionDetail;
import entities.Product;
import entities.Role;
import entities.User;

@Controller
@RequestMapping(value = "admincp")
public class AdminIndexController {
	@Autowired
	private Defines defines;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ExhibitionDao exhibitionDao;
	@Autowired
	private ExhibitionDetailDao exhibitionDetailDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private CountDao countDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private Decimalformat decimalformat;

	@ModelAttribute
	public void addCommons(ModelMap modelMap, Principal principal) {
		modelMap.addAttribute("defines", defines);
		if (!(principal == null)) {
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
		Count count=countDao.getItem(1);
		modelMap.addAttribute("count", count.getCount());
		modelMap.addAttribute("decimalformat", decimalformat);
		
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpSession session, Authentication authentication) {
		int tongdoanhthu=0;
		ArrayList<Exhibition> arrayList4=(ArrayList<Exhibition>)exhibitionDao.getItemsByPay();
		for (Exhibition exhibition : arrayList4) {
			ArrayList<ExhibitionDetail> arrayList3=(ArrayList<ExhibitionDetail>)exhibitionDetailDao.getItems(exhibition.getId());
			for (ExhibitionDetail exhibitionDetail : arrayList3) {
				tongdoanhthu=exhibitionDetail.getNumber()*exhibitionDetail.getPrice_product()+tongdoanhthu;
			}
		}
		ArrayList<Product> arrayList=(ArrayList<Product>) productDao.getItemsSortBuy(0, 4);
		int tongBuy=productDao.getItemsSortTTBuy();
		int sumContact=contactDao.getSum();
		ArrayList<Role> arrayListNhom=(ArrayList<Role>) roleDao.getDoanhthu();
		modelMap.addAttribute("lItemRole", roleDao.getItems());
		modelMap.addAttribute("lItemRoleNhom", arrayListNhom);
		modelMap.addAttribute("sumContact", sumContact);
		modelMap.addAttribute("lItemBuy", arrayList);
		modelMap.addAttribute("sumBuy", tongBuy);
		modelMap.addAttribute("TDT", tongdoanhthu);
		modelMap.addAttribute("numUser", userDao.getSum());
		return "admincp.index.index";
	}

	@RequestMapping(value = "403", method = RequestMethod.GET)
	public String error(ModelMap modelMap) {
		return "admincp.403";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "thongKEx", method = RequestMethod.POST)
	public void thongKEx(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(name = "date1", defaultValue = "") String date1,
			@RequestParam(name = "date2", defaultValue = "") String date2) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (date1.equals("") || date2.equals("")) {
			out.print("0");
		} else {
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Date dat = (Date) simpleDateFormat.parse(date2);
				dat.setDate(dat.getDate() + 1);
				date2 = simpleDateFormat.format(dat);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Exhibition> arrayList = (ArrayList<Exhibition>) exhibitionDao
					.getItemsByDate(date1, date2);
			ArrayList<Exhibition> arrayList2 = (ArrayList<Exhibition>) exhibitionDao
					.getItemsByDateEn(date1, date2);
			int doanhthu=0;
			for (Exhibition exhibition : arrayList2) {
				ArrayList<ExhibitionDetail> arrayList3=(ArrayList<ExhibitionDetail>)exhibitionDetailDao.getItems(exhibition.getId());
				for (ExhibitionDetail exhibitionDetail : arrayList3) {
					doanhthu=exhibitionDetail.getNumber()*exhibitionDetail.getPrice_product()+doanhthu;
				}
			}
			int tongdoanhthu=0;
			ArrayList<Exhibition> arrayList4=(ArrayList<Exhibition>)exhibitionDao.getItemsByPay();
			for (Exhibition exhibition : arrayList4) {
				ArrayList<ExhibitionDetail> arrayList3=(ArrayList<ExhibitionDetail>)exhibitionDetailDao.getItems(exhibition.getId());
				for (ExhibitionDetail exhibitionDetail : arrayList3) {
					tongdoanhthu=exhibitionDetail.getNumber()*exhibitionDetail.getPrice_product()+tongdoanhthu;
				}
			}
			float per=0;
			if(tongdoanhthu!=0){
				per=(float) doanhthu/tongdoanhthu;
			}
			per= (float)Math.round(per*10000)/100 ;
			out.print("<table class='table table-hover'>"
					+ "<thead>"
					+ "<tr>"
					+ "<th>#</th>"
					+ "<th>Số liệu</th>"
					+ "</tr>"
					+ "</thead>"
					
					+ "<tbody id='DThu1'>"
					+ "<tr>"
					+ "<td>1</td>"
					+ "<td>Doanh thu: "+decimalformat.change(doanhthu)+" VNĐ chiếm sấp xỉ <span style='background: red;' class='badge badge-success'>"+per+"%</span> tổng doanh thu "+decimalformat.change(tongdoanhthu)+" VNĐ</td>"
					+ "</tr>"

					+ "<tr>"
					+ "<td>2</td>"
					+ "<td>Tổng cộng có "+arrayList.size()+" đơn đặt hàng trong đó có "+arrayList2.size()+" đơn đặt hàng đã thanh toán</td>"
					+ "</tr>" + "</tbody>" + "</table>");
		}
	}
	
	@RequestMapping(value = "getCount", method = RequestMethod.POST)
	public void getCount(ModelMap modelMap, HttpServletRequest request, Model model,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Count count=countDao.getItem(1);
		out.print(count.getCount());
//		model.asMap().clear();
//		return "redirect:/admincp";
	}

}
