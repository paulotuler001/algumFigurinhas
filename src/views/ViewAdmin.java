package views;

import java.awt.*;

import javax.swing.*;

public class ViewAdmin extends JFrame{
	public ViewAdmin() {
		setTitle("User Frame Manager");
		 setSize(new Dimension(800, 600));
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setLocationRelativeTo(null);
//		 setResizable(false);
		 
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
		
		 
		 JPanel upBarContainer = new JPanel();
		 upBarContainer.setBackground(new Color(13, 62, 16));
		 upBarContainer.add(addUserBtn);
		 upBarContainer.add(removeUserBtn);
		 upBarContainer.add(editUserBtn);
		 upBarContainer.add(filterField);
		 upBarContainer.add(filterUserBtn);

		 add(upBarContainer);
		 
		 

		 
		 
	}
	
	public static void main(String args[]) {
		ViewAdmin va = new ViewAdmin();
		va.setVisible(true);

	}

}
