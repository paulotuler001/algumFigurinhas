package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class ViewFigureInfo extends JFrame {

	public ViewFigureInfo() {
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

		String[] pages = { "1", "2", "3" };
		JLabel pageLabel = new JLabel("Páginas");
		pageLabel.setForeground(Color.WHITE);
		JComboBox<String> dropdown = new JComboBox<>(pages);
		dropdown.setBackground(Color.WHITE);
		dropdown.setPreferredSize(new Dimension(150, dropdown.getPreferredSize().height));
		
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
                int resultado = fileChooser.showOpenDialog(ViewFigureInfo.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    campoArquivo.setText(arquivoSelecionado.getName());
                }
            }
        });
        
		JLabel tagLabel = new JLabel("Tag");
		tagLabel.setForeground(Color.WHITE);
		JTextField tagField = new JTextField(15);
		tagField.setBackground(Color.WHITE);
		JLabel descriptionLabel = new JLabel("Descrição");
		descriptionLabel.setForeground(Color.WHITE);
		JTextField descriptionField = new JTextField(15);
		descriptionField.setBackground(Color.WHITE);
		
		JButton okayBtn = new JButton("Ok");
		okayBtn.setFocusable(false);
		okayBtn.setBackground(Color.WHITE);
		okayBtn.setForeground(Color.black);
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.setFocusable(false);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setForeground(Color.black);
		
 		

		nameLabel.setBounds(yy - 100, xx  - 90, 100, 25);
		nameField.setBounds(yy - 40, xx - 90, 200, 25);
		pageLabel.setBounds(yy - 100, xx - 45, 185, 25);
		dropdown.setBounds(yy - 40, xx - 45, 75, 25);
		coverLabel.setBounds(yy - 100, xx - 5, 65, 25);
		campoArquivo.setBounds(yy - 40, xx - 5, 150, 25);
		btnSelecionar.setBounds(yy + 110, xx - 5, 45, 25);
		tagLabel.setBounds(yy - 100, xx + 35, 100, 25);
		tagField.setBounds(yy - 40, xx + 35, 200, 25);
		descriptionLabel.setBounds(yy - 100, xx + 75, 100, 25);
		descriptionField.setBounds(yy - 40, xx + 75, 200, 50);
		okayBtn.setBounds(yy - 50, xx + 175, 100, 25);
		cancelBtn.setBounds(yy + 150, xx + 175, 100, 25);
		


		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(13, 62, 16));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(pageLabel);
		panel.add(dropdown);
		panel.add(coverLabel);
		panel.add(campoArquivo);
		panel.add(btnSelecionar);
		panel.add(tagLabel);
		panel.add(tagField);
		panel.add(descriptionLabel);
		panel.add(descriptionField);
		panel.add(okayBtn);
		panel.add(cancelBtn);


		add(panel);
	}

	public static void main(String args[]) {
		ViewFigureInfo vf = new ViewFigureInfo();
		vf.setVisible(true);

	}

}
