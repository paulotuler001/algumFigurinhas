package views;

import java.awt.*;

import javax.swing.*;

public class ViewUser extends JFrame{
	public ViewUser() {
		setTitle("User Frame Login");
		 setSize(new Dimension(800, 600));
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setLocationRelativeTo(null);
//		 setResizable(false);
		 
			int xx = this.getHeight()/3;
			int yy = this.getWidth()/5;
		 
		JLabel loginLabel = new JLabel("Login");
	    loginLabel.setBackground(Color.WHITE);
		JTextField loginField = new JTextField(15);
		loginField.setBackground(Color.WHITE);
		
		JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setBackground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setBackground(Color.WHITE);
		
	    String[] profile = {"Author", "Collector", "Administrator"};
		JLabel profileLabel = new JLabel("Perfil");
		profileLabel.setBackground(Color.WHITE);
		JComboBox <String> dropdown = new JComboBox<>(profile);
		dropdown.setBackground(Color.WHITE);
		  
		loginLabel.setBounds(yy, xx, 165, 25);
		loginField.setBounds(yy+100, xx, 165, 25);
		passwordLabel.setBounds(yy, xx+30, 165, 25);
		passwordField.setBounds(yy+100, xx+30, 165, 25);
		profileLabel.setBounds(yy+25, xx+90, 80, 25);
		dropdown.setBounds(yy+125, xx+90, 80, 25);
		 
		 JButton okayrBtn = new JButton("okay");
		 okayrBtn.setFocusable(false);
		 okayrBtn.setBackground(Color.white);
		 JButton cancelBtn = new JButton("Cancelar");
		 cancelBtn.setFocusable(false);
		 cancelBtn.setBackground(Color.white);

		 JPanel panel = new JPanel();
		 panel.add(loginLabel);	
		 panel.add(loginField);	
		 panel.add(passwordLabel);
		 panel.add(passwordField);
		 panel.add(profileLabel);
		 panel.add(dropdown);
		 panel.add(okayrBtn);
		 panel.add(cancelBtn);

		 add(panel);
	}
	
	public static void main(String args[]) {
		ViewUser vu = new ViewUser();
		vu.setVisible(true);

	}

}
