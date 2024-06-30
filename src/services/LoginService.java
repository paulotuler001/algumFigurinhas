package services;

import entities.User;
import repositories.LoginRepository;

public class LoginService {
	
	LoginRepository lr = new LoginRepository();
	
	public User login(String login, String password) {
		User user = null;
		
		if (login.equals("admin") && password.equals("coxinha123"))
			user = lr.login(login, password);
		else
			user = lr.loginUser(login, password);
		
		return user;
	}
}
