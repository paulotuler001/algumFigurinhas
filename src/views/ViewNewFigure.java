package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class ViewNewFigure extends JFrame {

	public ViewNewFigure() {
		setTitle("Figure Info Frame");
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
		
		JLabel tagLabel = new JLabel("Tag");
		tagLabel.setForeground(Color.WHITE);

		
		JTextField codImage = new JTextField(30);
		codImage.setEditable(false);
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
                int resultado = fileChooser.showOpenDialog(ViewNewFigure.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    codImage.setText(arquivoSelecionado.getName());
                }
            }
        });
        
		JLabel numberLabel = new JLabel("Número");
		numberLabel.setForeground(Color.WHITE);
		JTextField numberField = new JTextField(15);
		numberField.setBackground(Color.WHITE);
		
		JButton addBtn = new JButton("Inserir");
		addBtn.setFocusable(false);
		addBtn.setBackground(Color.WHITE);
		addBtn.setForeground(Color.black);
		JButton returnBtn = new JButton("Voltar");
		returnBtn.setFocusable(false);
		returnBtn.setBackground(Color.WHITE);
		returnBtn.setForeground(Color.black);
		
 		


		tagLabel.setBounds(yy - 100, xx - 90, 100, 25);
		codImage.setBounds(yy - 40, xx - 90, 150, 25);
		btnSelecionar.setBounds(yy + 110, xx - 90, 45, 25);
		nameLabel.setBounds(yy - 100, xx  - 45, 100, 25);
		nameField.setBounds(yy - 40, xx - 45, 200, 25);
		pageLabel.setBounds(yy - 100, xx, 185, 25);
		pageField.setBounds(yy - 40, xx,  75, 25);
		numberLabel.setBounds(yy - 100, xx + 45, 100, 25);
		numberField.setBounds(yy - 40, xx + 45, 90, 25);
		addBtn.setBounds(yy - 50, xx + 155, 100, 25);
		returnBtn.setBounds(yy + 150, xx + 155, 100, 25);
		


		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(13, 62, 16));
		panel.add(tagLabel);
		panel.add(codImage);
		panel.add(btnSelecionar);
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(pageLabel);
		panel.add(pageField);
		panel.add(numberLabel);
		panel.add(numberField);
		panel.add(addBtn);
		panel.add(returnBtn);


		add(panel);
	}

	public static void main(String args[]) {
		ViewNewFigure vn = new ViewNewFigure();
		vn.setVisible(true);

	}

}
