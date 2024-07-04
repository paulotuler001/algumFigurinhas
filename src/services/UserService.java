package services;

import entities.User;
import repositories.UserRepository;

public class UserService {

	UserRepository ur = new UserRepository();
	
	public void save(User user) {

		
		System.out.println(user.getId());
		
		if (user.getName().length() < 1)
			throw new ExceptionService("Digite um nome válido");
		else if (user.getId() < 1)
			throw new ExceptionService("Digite um id válido");

		ur.save(user);
	}

	public User getUserById(Integer id) {

		if (id < 1)
			throw new ExceptionService("Digite um id inteiro válido");

		return ur.getUserById(id);
	}

	public Object[][] getAllUsers() {
		return ur.getAllUsers();
	}

	public void deleteUserById(Integer id) {

		if (id < 1)
			throw new ExceptionService("Digite um id inteiro válido");
		else if (!ur.getUserById(id).getActive())
			throw new ExceptionService("Usuário não encontrado");

		ur.deleteUserById(id);

	}

	public void editUserById(Integer id, User user) {

		if (id < 1 || user.getId() < 1)
			throw new ExceptionService("Digite um id inteiro válido");
		else if (!ur.getUserById(id).getActive())
			throw new ExceptionService("Author não encontrado");
		else if (user.getName().length() < 1)
			throw new ExceptionService("Digite um nome válido");

		ur.editUserById(id, user);

	}
}
