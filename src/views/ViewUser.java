package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import entities.User;
import enums.Role;
import repositories.UserRepository;
import services.UserService;

public class ViewUser extends JDialog {

	private Integer id;
	private String login;
	private String password;
	private Integer userProfile;

	public ViewUser(Integer id, String login, String password, Integer userProfile) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.userProfile = userProfile;

	}

	public void openDialog(JFrame parentFrame) {
		Boolean isCreate = login.equals(""); // se for true, é pra criar, se nao, pra editar
		JDialog dialog = new JDialog(parentFrame, "AddUser", true);
		dialog.setTitle("User Frame Login");
		dialog.setSize(new Dimension(800, 600));
//		setDefaultCloseOperation(1);
		dialog.setLocationRelativeTo(null);
//		 setResizable(false);

		int xx = 200;
		int yy = xx+50;
		
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
		

		JLabel loginLabel = new JLabel("Login");
		loginLabel.setForeground(Color.WHITE);
		JTextField loginField = new JTextField(15);
		loginField.setText(login);
		loginField.setBackground(Color.WHITE);

		JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setForeground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setText(password);
		passwordField.setBackground(Color.WHITE);

		String[] profile = { "Author", "Collector", "Administrator" };
		JLabel profileLabel = new JLabel("Perfil");
		profileLabel.setForeground(Color.WHITE);
		JComboBox<String> dropdown = new JComboBox<>(profile);
		dropdown.setSelectedIndex(userProfile);
		dropdown.setBackground(Color.WHITE);
		dropdown.setPreferredSize(new Dimension(150, dropdown.getPreferredSize().height));

		loginLabel.setBounds(yy, xx, 165, 25);
		loginField.setBounds(yy + 60, xx, 190, 25);
		passwordLabel.setBounds(yy, xx + 30, 165, 25);
		passwordField.setBounds(yy + 60, xx + 30, 190, 25);
		profileLabel.setBounds(yy, xx + 60, 165, 25);
		dropdown.setBounds(yy + 60, xx + 60, 190, 25);

		JButton okayBtn = new JButton("Ok");
		okayBtn.setFocusable(false);
		okayBtn.setBackground(Color.WHITE);
		okayBtn.setForeground(Color.black);
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.setFocusable(false);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setForeground(Color.black);
		cancelBtn.addActionListener(e -> dialog.dispose());

		okayBtn.setBounds(yy + 15, xx + 120, 100, 25);
		cancelBtn.setBounds(yy + 155, xx + 120, 100, 25);

		okayBtn.addActionListener(new ActionListener() {

			UserService us = new UserService();
			UserRepository ur = new UserRepository();

			public void actionPerformed(ActionEvent e) {
				Role role = dropdown.getSelectedIndex() == 0 ? Role.AUTHOR
						: dropdown.getSelectedIndex() == 1 ? Role.COLLECTIONATOR : Role.ADM;

				if (isCreate) {
					System.out.println("ta no lugar certo");
					User user = new User(ur.getNewId(), true, loginField.getText(), role, loginField.getText(),
							passwordField.getText());
					us.save(user);

				} else {
					User user = us.getUserById(id);
					user.setEmail(loginField.getText());
					user.setPassword(passwordField.getText());
					user.setRole(role);
					us.editUserById(user.getId(), user);
				}
				dialog.dispose();
			}
		});

		panel.setLayout(null);
		panel.setBackground(new Color(13, 62, 16));
		panel.add(loginLabel);
		panel.add(loginField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(profileLabel);
		panel.add(dropdown);
		panel.add(okayBtn);
		panel.add(cancelBtn);
		
		dialog.add(panel);
		dialog.setVisible(true);
	}

//	public static void main(String[] args) {
//		ViewUser vu = new ViewUser("zap", "zep", 2);
//		vu.setVisible(true);
//	}
}
