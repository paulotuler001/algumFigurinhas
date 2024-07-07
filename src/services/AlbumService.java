package services;

import entities.Album;
import repositories.AlbumRepository;

public class AlbumService {
	
	AlbumRepository ar = new AlbumRepository();
	
	public void saveAlbum(Album album) {
		
		if(album.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(album.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		
		ar.saveAlbum(album);
	}
	
	public Album getAlbumById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		return ar.getAlbumById(id);
	}
	public void editAlbum(Album album) {
		ar.editAlbum(album);
	}
	
	
	
}
