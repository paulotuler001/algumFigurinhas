package views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ViewCollectionator extends JFrame {
	
	public ViewCollectionator() {
		setTitle("Login");
		setSize(new Dimension(800,600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.png"));
    	setIconImage(icon.getImage());
	}
	
	public static void main(String[] args) {
		ViewCollectionator vc = new ViewCollectionator();
		vc.setVisible(true);
	}
}
