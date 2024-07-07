package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.table.DefaultTableModel;

import configuration.MusicPlayer;
import entities.User;
import services.UserService;

public class ViewAlbum extends JFrame {
	
	boolean vol = false;
	
	public ViewAlbum() {
		
		JFrame selFrame = this; 
		
		setTitle("Album Frame");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		int yy = 60;
		int xx = 167;
		
		JButton changePasswordBtn = new JButton("1");
		changePasswordBtn.setFocusable(false);
		changePasswordBtn.setBackground(Color.white);
		changePasswordBtn.setToolTipText("Change Password");
		
		JButton addnewFigureBtn = new JButton("2");
		addnewFigureBtn.setFocusable(false);
		addnewFigureBtn.setBackground(Color.white);
		addnewFigureBtn.setToolTipText("Add new figure");
		
		JButton aboutBtn = new JButton("3");
		aboutBtn.setFocusable(false);
		aboutBtn.setBackground(Color.white);
		aboutBtn.setToolTipText("About");
		
		changePasswordBtn.setBounds(xx-60, yy-35, 35, 35);
		addnewFigureBtn.setBounds(xx-14,yy-35, 35, 35);
		aboutBtn.setBounds(xx+32, yy-35, 35, 35);

		
		changePasswordBtn.setMargin(new Insets(2,2,2,2));
		addnewFigureBtn.setMargin(new Insets(2,2,2,2));
		aboutBtn.setMargin(new Insets(2,2,2,2));

		
		JPanel query = new JPanel();
		query.setLayout(null);
		query.setBorder(null);
		query.setBackground(new Color(13, 62, 16));
		query.setPreferredSize(new Dimension(800,480));

		String imagePath = "/images/5.jpg";
        ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image backgroundImage = backgroundImageIcon.getImage();
		
		JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800,600));
		panel.add(changePasswordBtn);
		panel.add(addnewFigureBtn);
		panel.add(aboutBtn);

        
		add(panel, BorderLayout.NORTH);
	}

	public static void main(String args[]) {
		ViewAlbum va = new ViewAlbum();
		va.setVisible(true);

	}

}
