package services;

import entities.Album;
import repositories.AlbumRepository;
import repositories.UserRepository;

public class AlbumService {
	
	AlbumRepository ar = new AlbumRepository();
	
	public void saveAlbum(Album album) {
		
		if(album.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(album.getAuthorId() < 1 || album.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		else if(album.getTotalLFigures() < 0) 
			throw new ExceptionService("Digite um número válido de figurinhas");
		
		UserRepository ur = new UserRepository();
		
		if(!ur.getUserById(album.getAuthorId()).getActive()) {
			throw new ExceptionService("Id de autor inexistente");
		}
		
		ar.saveAlbum(album);
	}
	
	public void getAlbumById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		ar.getAlbumById(id);
	}
	
	public void getAllAlbums() {
		ar.getAllAlbums();
	}
	
	public void deleteAlbumById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!ar.getAlbumById(id)) {
			throw new ExceptionService("Album não encontrado");
		}
		
		ar.deleteAlbumById(id);
		
	}
	
	public void editAlbumById(Integer id, Album album) {
		
		if(id < 1 || album.getId() < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!ar.getAlbumById(id)) 
			throw new ExceptionService("Album não encontrado");
		else if(album.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		ar.editAlbumById(id, album);
		
	}
	
	
}
