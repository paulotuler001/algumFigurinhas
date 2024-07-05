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

import entities.User;
import services.UserService;

public class ViewAdmin extends JFrame {
	public ViewAdmin() {
		setTitle("User Frame Manager");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		 setResizable(false);
		setLayout(new BorderLayout());
		

		int yy = 60;
		int xx = 167;
		
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

		JTextField filterField = new JTextField(15);
		
		
		JPanel query = new JPanel();

		String[] colunas = { "Nome", "Perfil" };
		UserService us = new UserService();
		
		DefaultTableModel model = new DefaultTableModel(us.getAllUsers(), colunas);
		JTable tabela = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.getViewport().setBackground(new Color(13, 62, 16));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabela.setBackground(Color.yellow);
		tabela.getTableHeader().setBackground(Color.WHITE);
		tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tabela.getTableHeader().setForeground(Color.RED);
		
		removeUserBtn.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                int selectedRow = (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
                
                if (selectedRow != -1) {
                	System.out.println(selectedRow);
                    us.deleteUserById(selectedRow);
//                    tabela.remove(tabela.getSelectedRow());
                    model.removeRow(tabela.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para apagar.");
                }
            }
        });
		JFrame selFrame = this; 

		editUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 0 é author, 1 collector, 2 adm
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
		
		
//		scrollPane.getVerticalScrollBar().setUI();
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
//		scrollPane.getVerticalScrollBar().set
//		Border borda = new Border();
//		scrollPane.setBorder(new RoundedBorder(10));

		addUserBtn.setBounds(xx, yy, 35, 35);
		removeUserBtn.setBounds(xx+38,yy, 35, 35);
		editUserBtn.setBounds(xx+76, yy, 35, 35);
		filterField.setBounds(xx+130, yy,280, 35);
		filterUserBtn.setBounds(xx+415, yy, 35, 35);
		
		addUserBtn.setMargin(new Insets(2,2,2,2));
		removeUserBtn.setMargin(new Insets(2,2,2,2));
		editUserBtn.setMargin(new Insets(2,2,2,2));
		filterUserBtn.setMargin(new Insets(2,2,2,2));
		
		
//		query.setLayout(null);
		query.add(scrollPane);
		query.setBorder(null);
		query.setBackground(new Color(13, 62, 16));

		JPanel upBarContainer = new JPanel();
		upBarContainer.setLayout(null);
		upBarContainer.setPreferredSize(new Dimension(100,100));
		upBarContainer.setBackground(new Color(13, 62, 16));
		upBarContainer.add(addUserBtn);
		upBarContainer.add(removeUserBtn);
		upBarContainer.add(editUserBtn);
		upBarContainer.add(filterField);
		upBarContainer.add(filterUserBtn);
		
		
		add(upBarContainer, BorderLayout.NORTH);
		add(query, BorderLayout.CENTER);
//		add(spacer);

	}

	public static void main(String args[]) {
		ViewAdmin va = new ViewAdmin();
		va.setVisible(true);

	}

}
