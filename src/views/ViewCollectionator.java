package views;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ViewCollectionator extends JFrame {
	
	public ViewCollectionator() {
		setTitle("Login");
		setSize(new Dimension(800,600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		ViewCollectionator vc = new ViewCollectionator();
		vc.setVisible(true);
	}
}
