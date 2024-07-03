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
		JTextField coverField = new JTextField(15);
		coverField.setBackground(Color.WHITE);
		
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
		
		
		JLabel profileLabel = new JLabel("Perfil");
		profileLabel.setForeground(Color.WHITE);


		nameLabel.setBounds(yy, xx, 165, 25);
		nameField.setBounds(yy + 100, xx, 165, 25);
		pageLabel.setBounds(yy, xx + 30, 165, 25);
		pageField.setBounds(yy + 100, xx + 30, 165, 25);
		coverLabel.setBounds(yy, xx + 60, 165, 25);
		coverField.setBounds(yy + 100, xx + 30, 165, 25);
		campoArquivo.setBounds(yy + 100, xx + 60, 145, 25);
		btnSelecionar.setBounds(yy + 240, xx + 60, 25, 25);


		JButton okayBtn = new JButton("Ok");
		okayBtn.setFocusable(false);
		okayBtn.setBackground(Color.WHITE);
		okayBtn.setForeground(Color.black);
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.setFocusable(false);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setForeground(Color.black);

		okayBtn.setBounds(yy + 25, xx + 120, 100, 25);
		cancelBtn.setBounds(yy + 145, xx + 120, 100, 25);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(13, 62, 16));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(pageLabel);
		panel.add(pageField);
		panel.add(coverLabel);
		panel.add(coverField);
		panel.add(campoArquivo);
		panel.add(btnSelecionar);
		panel.add(okayBtn);
		panel.add(cancelBtn);

		add(panel);
	}

	public static void main(String args[]) {
		ViewAuthor va = new ViewAuthor();
		va.setVisible(true);

	}

}
