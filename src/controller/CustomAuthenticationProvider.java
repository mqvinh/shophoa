package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import utils.StringLibrary;
import dao.CountDao;
import dao.UserDao;
import entities.Count;
import entities.User;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserDao userDao;
	@Autowired
	private CountDao countDao;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());
		password = StringLibrary.md5(password);
		ArrayList<User> arrayList = (ArrayList<User>) userDao.getItemByUn(username, password);
		if (arrayList.size() > 0 && arrayList.get(0).getEnabled()==1) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+arrayList.get(0).getRname()));
			Authentication auth1 = new UsernamePasswordAuthenticationToken(username,password, grantedAuths);
			Count count=(Count)countDao.getItem(1);
			if(count.getCount()>=0){
				count.setCount(count.getCount()+1);
				countDao.editItem(count);
			}
			return auth1;
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
