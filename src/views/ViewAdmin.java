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

		int xx = this.getHeight() / 3;
		int yy = this.getWidth() / 3;
		
        JButton addFigureBtn = new JButton("+");
		 addFigureBtn.setFocusable(false);
		 addFigureBtn.setBackground(Color.white);
		 JButton removeFigureBtn = new JButton("-");
		 removeFigureBtn.setFocusable(false);
		 removeFigureBtn.setBackground(Color.white);
		 JButton editFigureBtn = new JButton("E");
		 editFigureBtn.setFocusable(false);
		 editFigureBtn.setBackground(Color.white);
		 JButton filterFigureBtn = new JButton("F");
		 filterFigureBtn.setFocusable(false);
		 filterFigureBtn.setBackground(Color.white);
		 JTextField filterFigureField = new JTextField(15);
		
			
		addFigureBtn.setBounds(yy - 100, xx  - 90, 50, 25);
		removeFigureBtn.setBounds(yy - 45, xx  - 90, 50, 25);
		editFigureBtn.setBounds(yy + 10, xx - 90, 50, 25);
		filterFigureField.setBounds(yy + 90, xx - 90, 228, 25);
		filterFigureBtn.setBounds(yy + 320, xx - 90, 50, 25);
		


		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(13, 62, 16));
		panel.add(addFigureBtn);
		panel.add(removeFigureBtn);
		panel.add(editFigureBtn);
		panel.add(filterFigureField);
		panel.add(filterFigureBtn);

		add(panel);
	}
	
	public static void main(String args[]) {
		ViewAdmin va = new ViewAdmin();
		va.setVisible(true);

	}

}

