package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import dao.CountDao;
import entities.Count;


public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	public LogoutSuccessHandler(String defaultTargetURL) {
        this.setDefaultTargetUrl("/admincp");
   }

	@Autowired
	private CountDao countDao;
	
   @Override
   public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

	   Count count=(Count)countDao.getItem(1);
		if(count.getCount()>0){
			count.setCount(count.getCount()-1);
			countDao.editItem(count);
		}
        super.onLogoutSuccess(request, response, authentication);
   }
}
