package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import utils.StringLibrary;
import constant.Defines;
import dao.AdvertiseDao;
import dao.ContactDao;
import dao.IntroduceDao;
import dao.ProductDao;
import dao.SlideDao;
import dao.SpeciesDao;
import dao.TypeDao;
import dao.UserDao;
import entities.Contact;
import entities.Introduce;
import entities.Product;
import entities.User;

@Controller
@RequestMapping(value="")
public class PublicIndexController {
	@Autowired
	private Decimalformat decimalformat;
	@Autowired
	private Defines defines;
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
	private ContactDao contactDao;
	@Autowired
	private SlugUtils slugUtils;
	@Autowired
	private AdvertiseDao advertiseDao;
	
	@ModelAttribute
	public void addCommons(ModelMap modelMap, HttpSession session, Principal principal){
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("decimalformat", decimalformat);
		modelMap.addAttribute("slugU", slugUtils);
		if(!(principal==null)){
			User user=userDao.getItemByUn(principal.getName());
				session.setAttribute("ItemU", user);
				modelMap.addAttribute("ItemU", user);
		}
		if(session.getAttribute("ItemU")!=null){
			modelMap.addAttribute("ItemU", session.getAttribute("ItemU"));
		}
		
		if(session.getAttribute("shopcart")!=null){
			@SuppressWarnings("unchecked")
			ArrayList<Product> arrayList=(ArrayList<Product>) session.getAttribute("shopcart");
			int sum=0;
			for (Product product : arrayList) {
				sum=sum+product.getSoluong()*product.getPrice();
			}
			modelMap.addAttribute("sumProShop", arrayList.size());
			modelMap.addAttribute("sumMoney", sum);
		}else{
			modelMap.addAttribute("sumProShop", 0);
			modelMap.addAttribute("sumMoney", 0);
		}
		
		int sumS=speciesDao.getSum();
		modelMap.addAttribute("lNSpecies", speciesDao.getItemsNum(0, sumS));
		modelMap.addAttribute("lSpecies", speciesDao.getItems(0, sumS));
		
		int sumP=typeDao.getSum();
		modelMap.addAttribute("lNType", typeDao.getItemsNum(0, sumP));
		modelMap.addAttribute("lType", typeDao.getItems(0, sumP));
		
		int sumPro=productDao.getSum();
		modelMap.addAttribute("sumPro", sumPro);
		
		modelMap.addAttribute("ItemAdver", advertiseDao.getItems(0, advertiseDao.getSum()));
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(ModelMap modelMap){
		modelMap.addAttribute("index", "index");
		int sumPro=productDao.getSum();
		modelMap.addAttribute("lProduct", productDao.getItems(0, sumPro));
		modelMap.addAttribute("lProductSortBuy", productDao.getItemsSortBuy(0, sumPro));
		modelMap.addAttribute("lSlide", slideDao.getItems(0, slideDao.getSum()));
		modelMap.addAttribute("sumPro", sumPro);
		modelMap.addAttribute("title", "CHỢ HOA ONLINE!!!");
		return "public.index.index";
	}
	
	@RequestMapping(value="gioi-thieu", method=RequestMethod.GET)
	public String aboutus(ModelMap modelMap,
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "h", defaultValue = "0") int hien){
		int rowcount = defines.ROW_COUNT;
		if(hien!=0){
			rowcount = hien;
		}
		int sum = introduceDao.getSum();
		if(sum<rowcount){
			rowcount=sum;
		}
		int sumpage = (int) Math.ceil((float) sum / rowcount);
		modelMap.addAttribute("sumpage", sumpage);
		int offset = (page - 1) * rowcount;
		modelMap.addAttribute("index", "gioithieu");
		modelMap.addAttribute("lItem", introduceDao.getItems(offset, rowcount));
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("rowcount", rowcount);
		modelMap.addAttribute("sum", sum);
		modelMap.addAttribute("h", hien);
		modelMap.addAttribute("title", "Giới thiệu!!!");
		return "public.aboutus";
	}
	@RequestMapping(value="gioi-thieu/{slug}-{id}", method=RequestMethod.GET)
	public String aboutus(ModelMap modelMap, @PathVariable("slug") String slug, @PathVariable("id") int id){
		Introduce introduce=introduceDao.getItem(id);
		modelMap.addAttribute("Item", introduce);
		modelMap.addAttribute("index", "gioithieu");
		modelMap.addAttribute("title", "Giới thiệu"+introduce.getName());
		return "public.aboutus_detail";
	}

	@RequestMapping(value="lien-he", method=RequestMethod.GET)
	public String contact(ModelMap modelMap){
		modelMap.addAttribute("index", "lienhe");
		modelMap.addAttribute("contact", "contact");
		modelMap.addAttribute("title", "Liên hệ");
		return "public.contact";
	}
	
	@RequestMapping(value="sendcontact", method=RequestMethod.POST)
	public String sendcontact(ModelMap modelMap,
			@ModelAttribute("objItem") Contact objItem){
		if(objItem.getFullname().equals("")||objItem.getEmail().equals("")||objItem.getPhone().equals("")||objItem.getPreview().equals("")){
			return "redirect:/lien-he";
		}else{
			if(contactDao.addItem(objItem)>0){
				return "redirect:/lien-he";
			}
			else{
				return "redirect:/lien-he";
			}
		}
	}
	
	@RequestMapping(value="dang-nhap", method=RequestMethod.GET)
	public String login(ModelMap modelMap){
		modelMap.addAttribute("title", "Đăng nhập");
		return "public.login";
	}
	
	@RequestMapping(value="lay-pass", method=RequestMethod.GET)
	public String forgetpass(ModelMap modelMap){
		modelMap.addAttribute("title", "Lấy mật khẩu");
		return "public.forgetpass";
	}
	
	@RequestMapping(value="lay-pass", method=RequestMethod.POST)
	public String forgetpassP(ModelMap modelMap, @RequestParam(name="email", defaultValue="") String email,
			Model model){
		if(email.equals("")){
			model.asMap().clear();
			return "redirect:/lay-pass?msg=loi";
		}
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern  pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()){
			model.asMap().clear();
			return "redirect:/lay-pass?msg=loi";
		}
		ArrayList<User> arrayList=(ArrayList<User>) userDao.getItemByEmail(email);
		if(arrayList.size()<=0){
			model.asMap().clear();
			return "redirect:/lay-pass?msg=loi_tk";
		}
		arrayList.get(0).setPassword(StringLibrary.md5("12345678"));
		if(userDao.editItem(arrayList.get(0))<0){
			model.asMap().clear();
			return "redirect:/lay-pass?msg=loi_update";
		}
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", 587);
		Session s = Session.getInstance(p,
				 new javax.mail.Authenticator() {
				 protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication("maiquangvinhi4@gmail.com", "vinhmqmqvinh");
				 }
				});
		Message msg = new MimeMessage(s);
		try {
			msg.setFrom(new InternetAddress("maiquangvinhi4@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Lấy lại mật khẩu!!! từ shop hoa online");
			msg.setText("Sau khi có mật khẩu bạn hãy đăng nhập và thay đổi ngay để tránh bị mất cắp tài khoản"
					+ "Tên đăng nhập: "+arrayList.get(0).getUsername()+":Mật khẩu mới: 12345678");
			Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		model.asMap().clear();
		return "redirect:/lay-pass?msg=laypass";
	}
	
	@RequestMapping(value="dang-ky", method=RequestMethod.GET)
	public String register(ModelMap modelMap){
		modelMap.addAttribute("title", "Đăng ký");
		return "public.register";
	}
}
