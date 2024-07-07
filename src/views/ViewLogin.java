package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import configuration.MusicPlayer;
import entities.User;
import enums.Role;
import services.AlbumService;
import services.LittleFigureService;
import services.LoginService;
import services.UserService;

public class ViewLogin extends JFrame {

	boolean vol = false;
	
	public ViewLogin() {

		setTitle("Login");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//theme breaking bad
		float volume = -30.0f; //trolei
		MusicPlayer mp = new MusicPlayer();
		mp.playLoop();
		mp.setVolume(volume);		JButton mute = new JButton("🔇");
		mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vol) {
					mp.setVolume(volume);
					vol = false;
					mute.setText("🔉");
				}else {
					mp.setVolume(-100.0f);
					vol = true;
					mute.setText("🔇");
				}
			}
		});
		mute.setFont(new Font("", Font.BOLD, 15));
		mute.setMargin(new Insets(2,2,2,2));
		mute.setFocusable(false);
		mute.setForeground(Color.WHITE);
		mute.setBackground(Color.DARK_GRAY);
		
		
		int xx = this.getHeight() / 3;
		int yy = this.getWidth() / 3;
		URL url = getClass().getResource("/images/icon.png");
		ImageIcon appIcon = new ImageIcon(url);
		setIconImage(appIcon.getImage());

		String imagePath = "/images/6.jpg";
		ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource(imagePath));
		Image backgroundImage = backgroundImageIcon.getImage();

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(253, 353));

		JLabel usernameLabel = new JLabel("Login");
		usernameLabel.setForeground(Color.WHITE);
		JTextField usernameField = new JTextField(15);
		usernameField.setBackground(Color.WHITE);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setBackground(Color.WHITE);

		JButton loginButton = new JButton("Ok");
		loginButton.setFont(new Font("Georgia", Font.BOLD, 12));
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.WHITE);
		loginButton.setForeground(Color.black);

		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(new Font("Georgia", Font.BOLD, 12));
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
		mute.setBounds(0,0,40,40);
		
		
		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(loginButton);
		panel.add(cancelButton);
		panel.add(mute);
		add(panel);
		
		LoginService ls = new LoginService();
		JFrame selFrame = this;
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String login = usernameField.getText();
				String password = new String(passwordField.getPassword());
				User user = ls.login(login, password);
				if (user != null) {

					JOptionPane.showMessageDialog(null, "Login successful");
					JOptionPane.showMessageDialog(null, "Welcome back "+user.getRole().toString() + ": " + user.getName());
					mp.stop();
					
					SwingUtilities.invokeLater(() -> {
			            ViewSplash splash = new ViewSplash();
			            splash.showSplash();
					});
					
					if (user.getRole().equals(Role.ADM)) {
						ViewAdmin va = new ViewAdmin();
						va.setVisible(true);
					} else if (user.getRole().equals(Role.AUTHOR)) {
						ViewAuthor va = new ViewAuthor();
						va.setVisible(true);
					} else {
						ViewCollectionator vc = new ViewCollectionator();
						vc.setVisible(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect login");
				}
			}
		});
	}

	public static void main(String args[]) {

//		User author = new User(10, true, "Zap", Role.AUTHOR, "a@o.com", "123");
//		User adm = new User(11, null, "Zed", Role.ADM, "a@oo.com", "123");
//		User col = new User(99, true, "Zip", Role.COLLECTIONATOR, "a@ooo.com", "123");
//		
//		LittleFigureService lfs = new LittleFigureService();
//		UserService as = new UserService();
//		UserService ads = new UserService();
//		AlbumService als = new AlbumService();
//		UserService cs = new UserService();
//		
//		as.save(author);
//		ads.save(adm);
//		cs.save(col);
		
		
		ViewLogin vl = new ViewLogin();
		vl.setVisible(true);
	}
}
