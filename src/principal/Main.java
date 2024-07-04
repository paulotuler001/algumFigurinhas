package principal;

import entities.Album;
import entities.LittleFigure;
import entities.User;
import enums.Role;
import services.AlbumService;
import services.LittleFigureService;
import services.LoginService;
import services.UserService;
import views.ViewLogin;

public class Main {
	public static void main(String[] args) {
//		LittleFigure lFigure = new LittleFigure(2, "neymar", "tag", "foto", "descricao", 1, 1, 1, 2);
//		Album album = new Album(1, "Album", 1);

		User author = new User(10, true, "Zap", Role.AUTHOR, "a@o.com", "123");
		User adm = new User(11, null, "Zed", Role.ADM, "a@oo.com", "123");
		User col = new User(99, true, "Zip", Role.COLLECTIONATOR, "a@ooo.com", "123");
		
		LittleFigureService lfs = new LittleFigureService();
		UserService as = new UserService();
		UserService ads = new UserService();
		AlbumService als = new AlbumService();
		UserService cs = new UserService();
		
//		lfs.saveLF(lFigure);
//		als.saveAlbum(album);
		as.save(author);
		ads.save(adm);
		cs.save(col);
		
//		lfs.getAllLittleFigures();
//		as.getAllUsers();
//		ads.getUserById(1);
//		als.getAlbumById(1);
//		cs.getAllUsers();
		
//		lFigure.setName("messi");
//		lFigure.setDescription("mior do mundo");
//		lFigure.setPhoto("foto do messi pelado");
//		
//		author.setName("autorzada Paulozada");
//		author.setEmail("dale@mail.com");
//		
//		adm.setEmail("admin");
//		adm.setPassword("coxinha123");
//		
		
//		album.setName("album unico");
//		
//		col.setName("colecionadorzada Paulozada");
//		lfs.editLFById(2, lFigure);
//		as.editUserById(1, author);
//		ads.editUserById(2, adm);
//		cs.editUserById(3, col);
//		
//		lfs.getAllLittleFigures();
//		as.getAllUsers();
//		ads.getUserById(1);
//		als.getAlbumById(1);
//		cs.getAllUsers();
		
//		LoginService lr = new LoginService();
//		
//		String login = "a@bb.com";
//		String password = "123";
//		
//		lr.login(login, password);
//		
//		ViewLogin vl = new ViewLogin();
//		vl.setVisible(true);
		
	}
}
