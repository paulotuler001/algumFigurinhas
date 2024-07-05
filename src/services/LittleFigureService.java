package services;

import entities.LittleFigure;
import repositories.LittleFigureRepository;

public class LittleFigureService {
	
	LittleFigureRepository lfr = new LittleFigureRepository();
	
	public void saveLF(LittleFigure lf) {
		
		if(lf.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(lf.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		else if(lf.getNumber() < 0) 
			throw new ExceptionService("Digite um número válido");
		
		lfr.saveLF(lf);
	}
	
	public void getLFById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		lfr.getLFById(id);
	}
	
	public Object[][] getAllLittleFigures() {
		return lfr.getAllLittleFigures();
	}
	
	public void deleteLFById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!lfr.getLFById(id))
			throw new ExceptionService("Figurinha não encontrada");
		
		lfr.deleteLFById(id);
		
	}
	
	public void editLFById(Integer id, LittleFigure lf) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!lfr.getLFById(id)) 
			throw new ExceptionService("Figurinha não encontrada");
		else if(lf.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		lfr.editLFById(id, lf);
		
	}
	
}
