package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import entities.User;
import enums.Role;
import services.LoginService;

public class ViewLogin extends JFrame{
	
	public ViewLogin() {
		
		setTitle("Login");
		setSize(new Dimension(800,600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		int xx = this.getHeight()/3;
		int yy = this.getWidth()/3;
		
		String imagePath = "C:\\Users\\Public\\a\\6.jpg";
        ImageIcon backgroundImageIcon = new ImageIcon(imagePath);
        Image backgroundImage = backgroundImageIcon.getImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
		
		JLabel usernameLabel = new JLabel("Login");
		usernameLabel.setForeground(Color.WHITE);
		JTextField usernameField = new JTextField(15);
		usernameField.setBackground(Color.WHITE);
		
		JLabel passwordLabel = new JLabel("Senha:");
		passwordLabel.setForeground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setBackground(Color.WHITE);
		
		JButton loginButton = new JButton("Ok");
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.WHITE);
		loginButton.setForeground(Color.black);
		
		JButton cancelButton = new JButton("Sair");
		cancelButton.setFocusable(false);
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setForeground(Color.black);
		cancelButton.addActionListener(e -> dispose());
		
		usernameLabel.setBounds(yy, xx, 165, 25);
		usernameField.setBounds(yy + 60, xx, 165, 25);
		passwordLabel.setBounds(yy, xx + 30, 165, 25);
		passwordField.setBounds(yy + 60, xx + 30, 165, 25);
		loginButton.setBounds(yy + 25, xx + 90, 80, 25);
		cancelButton.setBounds(yy + 140, xx + 90, 80, 25);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(253,353));
		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(loginButton);
		panel.add(cancelButton);
		add(panel);
		
        LoginService ls = new LoginService();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = ls.login(login, password);
                if ( user != null) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    JOptionPane.showMessageDialog(null, "Welcome back " + user.getName());
                    
                    if(user.getRole().equals(Role.ADM)){
                    	ViewAdmin va = new ViewAdmin();
						va.setVisible(true);						
					}else{
						ViewAuthor va = new ViewAuthor();
						va.setVisible(true);	
					}
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect login");
                }
            }
        });
	}
	public static void main(String args[]) {
		
		ViewLogin vl = new ViewLogin();
		vl.setVisible(true);
	}
}

