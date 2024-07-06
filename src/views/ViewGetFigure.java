package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class ViewGetFigure extends JFrame {
	
	private JLabel label;

	public ViewGetFigure() {
		setTitle("Figure Info Frame");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		 setResizable(false);

		int xx = this.getHeight() / 3;
		int yy = this.getWidth() / 3;
		
		String imagePath = "/images/gradient.jfif";
        ImageIcon backgroundImageIcon = new ImageIcon(imagePath);
        Image backgroundImage = backgroundImageIcon.getImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setForeground(Color.WHITE);
		JTextField nameField = new JTextField(15);
		nameField.setBackground(Color.WHITE);

		JLabel pageLabel = new JLabel("Página");
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
                    	  return file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg")|| file.isDirectory();
                    }
                    public String getDescription() {
                    	return "Imagens (*.jpg, *.jpeg, *.png)";
                    }
                });
                int resultado = fileChooser.showOpenDialog(ViewGetFigure.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    codImage.setText(arquivoSelecionado.getName());
                    ImageIcon icon = new ImageIcon(arquivoSelecionado.getPath());
                    Image image = icon.getImage();
                    Image resizedImage = image.getScaledInstance(220, 250, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    label.setIcon(resizedIcon);
                    label.setBounds(yy + 180, xx - 100, 220, 250);
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
		label = new JLabel();
		
 		


		tagLabel.setBounds(yy - 100, xx - 90, 100, 25);
		codImage.setBounds(yy - 40, xx - 90, 150, 25);
		btnSelecionar.setBounds(yy + 110, xx - 90, 45, 25);
		nameLabel.setBounds(yy - 100, xx  - 45, 100, 25);
		nameField.setBounds(yy - 40, xx - 45, 200, 25);
		pageLabel.setBounds(yy - 100, xx, 185, 25);
		pageField.setBounds(yy - 40, xx,  75, 25);
		numberLabel.setBounds(yy - 100, xx + 45, 100, 25);
		numberField.setBounds(yy - 40, xx + 45, 90, 25);
		addBtn.setBounds(yy - 50, xx + 175, 100, 25);
		returnBtn.setBounds(yy + 150, xx + 175, 100, 25);
		

		panel.setLayout(null);
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
        panel.add(label);


		add(panel);
	}

	public static void main(String args[]) {
		ViewGetFigure vn = new ViewGetFigure();
		vn.setVisible(true);

	}

}
