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

public class ViewAdmin extends JFrame {
	
	boolean vol = false;
	
	public ViewAdmin() {
		
		JFrame selFrame = this; 
		
		setTitle("Admin Frame");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//theme breaking bad
//		float volume = -30.0f;
//		MusicPlayer mp = new MusicPlayer();
//		mp.playLoop();
//		mp.setVolume(-30.0f);
//		JButton mute = new JButton("🔇");
//		mute.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(vol) {
//					mp.setVolume(volume);
//					vol = false;
//					mute.setText("🔉");
//				}else {
//					mp.setVolume(-100.0f);
//					vol = true;
//					mute.setText("🔇");
//				}
//			}
//		});
//		mute.setFont(new Font("", Font.BOLD, 15));
//		mute.setMargin(new Insets(2,2,2,2));
//		mute.setFocusable(false);
//		mute.setForeground(Color.WHITE);
//		mute.setBackground(Color.DARK_GRAY);

		ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.png"));
    	setIconImage(icon.getImage());

		int yy = 60;
		int xx = 167;
		
		JButton addUserBtn = new JButton("+");
		addUserBtn.setFocusable(false);
		addUserBtn.setBackground(Color.white);
		
		JButton removeUserBtn = new JButton("-");
		removeUserBtn.setFocusable(false);
		removeUserBtn.setBackground(Color.white);
		
		JButton editUserBtn = new JButton("E");
		editUserBtn.setFocusable(false);
		editUserBtn.setBackground(Color.white);
		
		JButton filterUserBtn = new JButton("F");
		filterUserBtn.setFocusable(false);
		filterUserBtn.setBackground(Color.white);
		
		JButton backBtn = new JButton("\u2190");
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setFont(new Font("", Font.BOLD, 20));
		backBtn.setForeground(Color.WHITE);
		
		JTextField filterField = new JTextField(15);
		

		String[] columns = { "E-mail", "Profile" };
		UserService us = new UserService();
		DefaultTableModel model = new DefaultTableModel(us.getAllUsers(), columns);
		JTable tabela = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabela);
		
		JLabel userLabel = new JLabel("Users");
		userLabel.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE, 18));
		userLabel.setForeground(Color.white);
		
		
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getViewport().setBackground(Color.DARK_GRAY);
		scrollPane.setBorder(new EmptyBorder(15, 5, 5, 5));
		scrollPane.setBackground(Color.DARK_GRAY);
		tabela.setBackground(new Color(162, 219, 118));
		tabela.getTableHeader().setBackground(Color.DARK_GRAY);
		tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tabela.getTableHeader().setForeground(Color.WHITE);
		
		removeUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	int selectedRow = (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
                
                if (selectedRow != -1) {
                	System.out.println(selectedRow);
                    us.deleteUserById(selectedRow);
                    model.removeRow(tabela.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para apagar.");
                }
            }
        });
		
		

		editUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 0 = author, 1 = collector, 2 = adm
                int selectedRow = (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
				
				User user = us.getUserById(selectedRow);
				
				System.out.println(user.getRole().toString());
				ViewUser vu = new ViewUser(user.getId(),user.getEmail(), user.getPassword(), user.getRole().toString().equals("AUTHOR")? 0 : user.getRole().toString().equals("COLLECTIONATOR") ? 1 : 2 );
				vu.openDialog(selFrame);
				dispose();
				ViewAdmin adminRefreshed = new ViewAdmin();
				adminRefreshed.setVisible(true);
			}
		});
		
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUser vu = new ViewUser(1,"", "", 0);
				vu.openDialog(selFrame);
				dispose();
				ViewAdmin adminRefreshed = new ViewAdmin();
				adminRefreshed.setVisible(true);
			}
		});
		
		filterField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO filter
				
//				String searchText = filterField.getText().toLowerCase();
//				Stream<Object[]> filteredUsers = Arrays.stream(us.getAllUsers())
//						.filter(user -> user[1].toString().toLowerCase().contains(searchText))
//						.map(user -> new Object[]{user[0], user[1]});
//				List<Object[]> filteredUserss = new ArrayList<>();
			}
		});
		
		backBtn.addActionListener(e -> dispose());
		
		addUserBtn.setBounds(xx, yy, 35, 35);
		removeUserBtn.setBounds(xx+38,yy, 35, 35);
		editUserBtn.setBounds(xx+76, yy, 35, 35);
		filterField.setBounds(xx+130, yy,280, 35);
		filterUserBtn.setBounds(xx+415, yy, 35, 35);
		scrollPane.setBounds(xx-40, yy+100, 550, 250);
		userLabel.setBounds(xx-50, yy, 200,200);
		backBtn.setBounds(0,0,35,35);
		//mute.setBounds(50,0,35,35);
		
		addUserBtn.setMargin(new Insets(2,2,2,2));
		removeUserBtn.setMargin(new Insets(2,2,2,2));
		editUserBtn.setMargin(new Insets(2,2,2,2));
		filterUserBtn.setMargin(new Insets(2,2,2,2));
		backBtn.setMargin(new Insets(2,2,2,2));
		
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
		panel.add(addUserBtn);
		panel.add(removeUserBtn);
		panel.add(editUserBtn);
		panel.add(filterField);
		panel.add(filterUserBtn);
		panel.add(userLabel);
		panel.add(scrollPane);
		panel.add(backBtn);
		//panel.add(mute);
        
		add(panel, BorderLayout.NORTH);
	}

	public static void main(String args[]) {
		ViewAdmin va = new ViewAdmin();
		va.setVisible(true);

	}

}
