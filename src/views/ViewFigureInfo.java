package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import javax.swing.*;

import entities.LittleFigure;
import entities.User;
import services.LittleFigureService;

public class ViewFigureInfo extends JDialog {

	private JLabel label;
	private JLabel labelHash;
	private File arquivoSelecionado;
	private String hashMD5;
	private User author;
	
	public ViewFigureInfo(User author) {
		this.author = author;
	}
	
	public void openDialog(JFrame parentFrame) {

		JDialog dialog = new JDialog(parentFrame, "Add Figure", true);
		dialog.setTitle("Figure Info Frame");
		dialog.setSize(new Dimension(800, 600));
		dialog.setLocationRelativeTo(null);
//		 setResizable(false);

		int xx = 200;
		int yy = xx+50;

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

		JTextField tagField = new JTextField(15);
		tagField.setBackground(Color.WHITE);

		JLabel coverLabel = new JLabel("Figurinha");
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
						return file.getName().toLowerCase().endsWith(".png")
								|| file.getName().toLowerCase().endsWith(".jpg")
								|| file.getName().toLowerCase().endsWith(".jpeg") || file.isDirectory();
					}

					public String getDescription() {
						return "Imagens (*.jpg, *.jpeg, *.png)";
					}

					private String getExtension(File file) {
						String ext = null;
						String fileName = file.getName();
						int index = fileName.lastIndexOf('.');
						if (index > 0 && index < fileName.length() - 1) {
							ext = fileName.substring(index + 1).toLowerCase();
						}
						return ext;
					}
				});
				int resultado = fileChooser.showOpenDialog(ViewFigureInfo.this);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					arquivoSelecionado = fileChooser.getSelectedFile();
					campoArquivo.setText(arquivoSelecionado.getName());
					ImageIcon icon = new ImageIcon(arquivoSelecionado.getPath());
					Image image = icon.getImage();
					Image resizedImage = image.getScaledInstance(220, 250, Image.SCALE_SMOOTH);
					ImageIcon resizedIcon = new ImageIcon(resizedImage);
					label.setIcon(resizedIcon);
					label.setBounds(yy + 180, xx - 100, 220, 250);
					hashMD5 = calcularHashMD5(arquivoSelecionado);
					tagField.setText(hashMD5);

				}
			}
		});

		labelHash = new JLabel("Hash MD5: ");
		labelHash.setForeground(Color.WHITE);
		JLabel tagLabel = new JLabel("Tag");
		tagLabel.setForeground(Color.WHITE);

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
		label = new JLabel();

		okayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LittleFigureService lfs = new LittleFigureService();
				LittleFigure lf = new LittleFigure(1, "aaw", tagField.getText(),
						convertFileToByteArray(arquivoSelecionado), descriptionField.getText(),
						dropdown.getSelectedIndex(), 3, 0, 
						1 //author.getId()
						);
				lfs.saveLF(lf);
				JOptionPane.showMessageDialog(null, "Figurinha criada com sucesso"); 
				dialog.dispose();
			}
		});

		nameLabel.setBounds(yy - 100, xx - 90, 100, 25);
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
		panel.add(label);
		panel.add(labelHash);

		dialog.add(panel);
		dialog.setVisible(true);
	}

	private String calcularHashMD5(File arquivo) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try (DigestInputStream dis = new DigestInputStream(new FileInputStream(arquivo), md)) {
//				while (dis.read() != -1); bella???? aidjawidjawidj
				md = dis.getMessageDigest();
			}

			byte[] hashBytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : hashBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] convertFileToByteArray(File file)  {
		// Usar FileInputStream para ler o arquivo
		try (FileInputStream fis = new FileInputStream(file); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

			byte[] buffer = new byte[1024];
			int bytesRead;

			// Ler o arquivo e escrever no ByteArrayOutputStream
			while ((bytesRead = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			// Retornar o array de bytes
			return bos.toByteArray();
		} catch (IOException e) {
			e.getMessage();
			return null;
		}
	}

//	public static void main(String args[]) {
////		SwingUtilities.invokeLater(() -> {
////			User user = null;
////			ViewFigureInfo vf = new ViewFigureInfo(user);
////			vf.setVisible(true);
////		});
//
//	}

}