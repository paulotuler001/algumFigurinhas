package principal;

import entities.Administrator;
import entities.Album;
import entities.Author;
import entities.Collectionator;
import entities.LittleFigure;
import enums.Role;
import repositories.LittleFigureRepository;
import services.AdminService;
import services.AlbumService;
import services.AuthorService;
import services.CollectionatorService;
import services.LittleFigureService;
import repositories.AdminRepository;
import repositories.AlbumRepository;
import repositories.AuthorRepository;
import repositories.CollectionatorRepository;

public class Main {
	public static void main(String[] args) {
		LittleFigure lFigure = new LittleFigure(1, "neymar", "tag", "foto", "descricao", 1, 1, 1, 2);
		Author author = new Author(1, true, "Paulo", Role.AUTHOR, "a@b.com", "123", "");
		Administrator adm = new Administrator(1, "PAULO", Role.ADM);
		Album album = new Album(1, "Album", 1);
		Collectionator col = new Collectionator(1, true, "paulo", Role.COLLECTIONATOR, "a@bb.com", "123", "");
		
		LittleFigureService lfs = new LittleFigureService();
		AuthorService as = new AuthorService();
		AdminService ads = new AdminService();
		AlbumService als = new AlbumService();
		CollectionatorService cs = new CollectionatorService();
		
		LittleFigureRepository lfr = new LittleFigureRepository();
		lfr.createTable();
		
		AuthorRepository ar = new AuthorRepository();
		ar.createTable();
		
		AdminRepository adr = new AdminRepository();
		adr.createTable();
		
		AlbumRepository alr = new AlbumRepository();
		alr.createTable();
		
		CollectionatorRepository cr = new CollectionatorRepository();
		cr.createTable();
		
		lfs.saveLF(lFigure);
		as.saveAuthor(author);
		ads.saveAdmin(adm);
		als.saveAlbum(album);
		cs.saveCollectionator(col);
		
		lfs.getAllLittleFigures();
		as.getAllAuthors();
		ads.getAdminById(1);
		als.getAlbumById(1);
		cs.getAllCollectionators();
		
		lFigure.setName("messi");
		lFigure.setDescription("mior do mundo");
		lFigure.setPhoto("foto do messi pelado");
		
		author.setName("autorzada Paulozada");
		author.setEmail("dale@mail.com");
		
		adm.setName("guga");
		
		album.setName("album unico");
		
		col.setName("colecionadorzada Paulozada");
		lfs.editLFById(1, lFigure);
		as.editAuthorById(1, author);
		ads.editAdminById(1, adm);
		cs.editCollectionatorById(1, col);
		
		lfs.getAllLittleFigures();
		as.getAllAuthors();
		ads.getAdminById(1);
		als.getAlbumById(1);
		cs.getAllCollectionators();
		
		
	}
}
