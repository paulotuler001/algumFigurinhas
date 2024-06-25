package services;

import entities.Collectionator;
import repositories.CollectionatorRepository;

public class CollectionatorService {
	
	CollectionatorRepository cr = new CollectionatorRepository();
	
	public void saveCollectionator(Collectionator col) {
		
		if(col.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(col.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		
		cr.saveCollectionator(col);
		
	}
	
	public void getCollectionatorById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		cr.getCollectionatorById(id);
		
	}
	
	public void getAllCollectionators() {
		cr.getAllCollectionator();
	}
	
	public void deleteCollectionatorById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		cr.deleteCollectionatorById(id);
	}
	
	public void editCollectionatorById(Integer id, Collectionator col) {
		
		if(id < 1 || col.getId() < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!cr.getCollectionatorById(id)) 
			throw new ExceptionService("Colecionador não encontrado");
		else if(col.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		
		cr.editCollectionatorById(id, col);
	}
	
	
}
