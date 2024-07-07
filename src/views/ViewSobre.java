package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import configuration.MusicPlayer;
import entities.User;
import enums.Role;

public class ViewSobre extends JFrame {

	boolean vol = false;
	
	public ViewSobre() {

		setTitle("Sobre");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//theme breaking bad
		float volume = -30.0f;
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

		String imagePath = "/images/sobre.jpg";
		ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource(imagePath));
		Image backgroundImage = backgroundImageIcon.getImage();
		JButton back = new JButton("\u2190");
		back.addActionListener(e-> dispose());
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

		mute.setBounds(0,0,40,40);
		back.setBounds(50,0,40,40);
		back.setToolTipText("Log Out");
		back.addActionListener(e -> dispose());
		back.setMargin(new Insets(3,3,3,3));
		back.setFocusable(false);
		back.setBackground(Color.DARK_GRAY);
		back.setFont(new Font("", Font.BOLD, 20));
		back.setForeground(Color.WHITE);
		
		panel.add(mute);
		panel.add(back);
		add(panel);
		
	}

	public static void main(String args[]) {		
		ViewSobre vs = new ViewSobre();
		vs.setVisible(true);
	}
}
