package services;

import entities.Administrator;
import repositories.AdminRepository;

public class AdminService {

	AdminRepository adr = new AdminRepository();
	
	public void saveAdmin(Administrator adm) {
		
		if(adm.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(adm.getId() < 1 || adm.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		
		adr.saveAdmin(adm);
	}
	
	public void getAdminById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		adr.getAdminById(id);
	}
	
	public void editAdminById(Integer id, Administrator adm) {
		
		if(id < 1 || adm.getId() < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!adr.getAdminById(id)) 
			throw new ExceptionService("Usuário não encontrado");
		else if(adm.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		adr.editAdminById(id, adm);
		
	}
	
}
