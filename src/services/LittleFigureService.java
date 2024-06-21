package services;

import entities.Album;
import entities.LittleFigure;
import repositories.AlbumRepository;
import repositories.LittleFigureRepository;
import repositories.UserRepository;

public class LittleFigureService {
	
	LittleFigureRepository lfr = new LittleFigureRepository();
	
	public void saveLF(LittleFigure lf) {
		
		if(lf.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(lf.getIdAlbum() < 1 || lf.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		else if(lf.getNumberInAlbum() < 0) 
			throw new ExceptionService("Digite um número válido");
		
		AlbumRepository ar = new AlbumRepository();
		
		if(!ar.getAlbumById(lf.getIdAlbum())) {
			throw new ExceptionService("Id de album inexistente");
		}
		
		lfr.saveLF(lf);
	}
	
	public void getLFById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		lfr.getLFById(id);
	}
	
	public void getAllAlbums() {
		lfr.getAllLittleFigures();
	}
	
	public void deleteLFById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!lfr.getLFById(id))
			throw new ExceptionService("Figurinha não encontrada");
		
		lfr.deleteLFById(id);
		
	}
	
	public void editLFById(Integer id, LittleFigure lf) {
		
		if(id < 1 || lf.getId() < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!lfr.getLFById(id)) 
			throw new ExceptionService("Figurinha não encontrada");
		else if(lf.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		lfr.editLFById(id, lf);
		
	}
	
}
