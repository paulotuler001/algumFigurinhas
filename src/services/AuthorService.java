package services;

import entities.Author;
import repositories.AuthorRepository;

public class AuthorService {
	AuthorRepository ar = new AuthorRepository();
	
	public void saveAuthor(Author author) {
		
		if(author.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(author.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		
		ar.saveAuthor(author);
	}
	
	public void getAuthorById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		ar.getAuthorById(id);
	}
	
	public void getAllAuthors() {
		ar.getAllAuthors();
	}
	
	public void deleteAuthorById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!ar.getAuthorById(id))
			throw new ExceptionService("Author não encontrado");
		
		ar.deleteAuthorById(id);
		
	}
	
	public void editAuthorById(Integer id, Author author) {
		
		if(id < 1 || author.getId() < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(!ar.getAuthorById(id)) 
			throw new ExceptionService("Author não encontrado");
		else if(author.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		ar.editAuthorById(id, author);
		
	}
}
