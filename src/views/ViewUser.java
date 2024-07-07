package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

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
		Boolean isCreate = login.equals("");
		JDialog dialog = new JDialog(parentFrame, "Add User", true);
		dialog.setTitle("User Frame Login");
		dialog.setSize(new Dimension(800, 600));
		dialog.setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.png"));
    	dialog.setIconImage(icon.getImage());

		int xx = 200;
		int yy = xx+50;
		
		String imagePath = "/images/5.jpg";
        ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource(imagePath));
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

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setBackground(Color.WHITE);

		String[] profile = { "Author", "Collector", "Administrator" };
		JLabel profileLabel = new JLabel("Profile");
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
		JButton cancelBtn = new JButton("Cancel");
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
				char[] currentPassword = passwordField.getPassword();
				if (isCreate) {
					User user = new User(ur.getNewId(), true, loginField.getText(), role, loginField.getText(),
							BCrypt.hashpw(new String(currentPassword), BCrypt.gensalt()));
					us.save(user);

				} else {
					User user = us.getUserById(id);
					user.setEmail(loginField.getText());
					user.setPassword(BCrypt.hashpw(new String(currentPassword), BCrypt.gensalt()));
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

}
