package views;

import java.awt.*;
import javax.swing.*;

public class ViewUser extends JFrame {

	public ViewUser() {
		setTitle("User Frame Login");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		 setResizable(false);

		int xx = this.getHeight() / 3;
		int yy = this.getWidth() / 3;

		JLabel loginLabel = new JLabel("Login");
		loginLabel.setForeground(Color.WHITE);
		JTextField loginField = new JTextField(15);
		loginField.setBackground(Color.WHITE);

		JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setForeground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setBackground(Color.WHITE);

		String[] profile = { "Author", "Collector", "Administrator" };
		JLabel profileLabel = new JLabel("Perfil");
		profileLabel.setForeground(Color.WHITE);
		JComboBox<String> dropdown = new JComboBox<>(profile);
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

		okayBtn.setBounds(yy + 15, xx + 120, 100, 25);
		cancelBtn.setBounds(yy + 155, xx + 120, 100, 25);

		JPanel panel = new JPanel();
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

		add(panel);
	}

	public static void main(String args[]) {
		ViewUser vu = new ViewUser();
		vu.setVisible(true);

	}

}
