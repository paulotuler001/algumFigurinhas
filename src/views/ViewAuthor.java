package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class ViewAuthor extends JFrame {

	public ViewAuthor() {
		setTitle("Auhtor Frame");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		 setResizable(false);

		int xx = this.getHeight() / 3;
		int yy = this.getWidth() / 3;

		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setForeground(Color.WHITE);
		JTextField nameField = new JTextField(15);
		nameField.setBackground(Color.WHITE);

		JLabel pageLabel = new JLabel("Páginas");
		pageLabel.setForeground(Color.WHITE);
		JTextField pageField = new JTextField(15);
		pageField.setBackground(Color.WHITE);
		
		JLabel coverLabel = new JLabel("Capa");
		coverLabel.setForeground(Color.WHITE);

		
		JTextField campoArquivo = new JTextField(30);
		campoArquivo.setEditable(false);
		JButton btnSelecionar = new JButton("Selecionar Arquivo");
		btnSelecionar.setFocusable(false);
        btnSelecionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(File file) {
                        return file.getName().toLowerCase().endsWith(".png") || file.isDirectory();
                    }
                    public String getDescription() {
                        return "Arquivos png (*.png)";
                    }
                });
                int resultado = fileChooser.showOpenDialog(ViewAuthor.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    campoArquivo.setText(arquivoSelecionado.getName());
                }
            }
        });
		
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
		 JButton removeFigureAllBtn = new JButton("L");
		 removeFigureAllBtn.setFocusable(false);
		 removeFigureAllBtn.setBackground(Color.white);
		 JTextField filterFigureField = new JTextField(15);
		
		

		nameLabel.setBounds(yy - 100, xx  - 90, 185, 25);
		nameField.setBounds(yy - 50, xx - 90, 415, 25);
		pageLabel.setBounds(yy - 100, xx - 50, 185, 25);
		pageField.setBounds(yy - 50, xx - 50, 75, 25);
		coverLabel.setBounds(yy + 50, xx - 50, 65, 25);
		campoArquivo.setBounds(yy + 90, xx - 50, 230, 25);
		btnSelecionar.setBounds(yy + 320, xx - 50, 45, 25);
		addFigureBtn.setBounds(yy - 90, xx + 20, 50, 25);
		removeFigureBtn.setBounds(yy - 35, xx + 20, 50, 25);
		editFigureBtn.setBounds(yy + 20, xx + 20, 50, 25);
		filterFigureField.setBounds(yy + 90, xx + 20, 168, 25);
		filterFigureBtn.setBounds(yy + 260, xx + 20, 50, 25);
		removeFigureAllBtn.setBounds(yy + 315, xx + 20, 50, 25);
		


		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(13, 62, 16));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(pageLabel);
		panel.add(pageField);
		panel.add(coverLabel);
		panel.add(campoArquivo);
		panel.add(btnSelecionar);
		panel.add(addFigureBtn);
		panel.add(removeFigureBtn);
		panel.add(editFigureBtn);
		panel.add(filterFigureField);
		panel.add(filterFigureBtn);
		panel.add(removeFigureAllBtn);

		add(panel);
	}

	public static void main(String args[]) {
		ViewAuthor va = new ViewAuthor();
		va.setVisible(true);

	}

}
