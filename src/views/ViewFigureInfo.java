package views;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import entities.LittleFigure;
import entities.User;
import repositories.LittleFigureRepository;
import services.LittleFigureService;

public class ViewFigureInfo extends JDialog {

	private JLabel label;
	private File selectedFile;
	private String hashMD5;
	private LittleFigure lf;
	
	public ViewFigureInfo(LittleFigure lf) {
		this.lf = lf;
	}
	
	public void openDialog(JFrame parentFrame) {

		JDialog dialog = new JDialog(parentFrame, "Add Figure", true);
		dialog.setTitle("New Figure Frame");
		dialog.setSize(new Dimension(800, 600));
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		int xx = 200;
		int yy = xx+50;
		int fieldYY = yy - 28;
		
		//app icon
		ImageIcon icon = new ImageIcon("images\\icon.png");
    	dialog.setIconImage(icon.getImage());
    	
    	//background image
		String imagePath = "images\\5.jpg";
        ImageIcon backgroundImageIcon = new ImageIcon(imagePath);
        Image backgroundImage = backgroundImageIcon.getImage();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //name
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setForeground(Color.WHITE);
		JTextField nameField = new JTextField(15);
		nameField.setBackground(Color.WHITE);
		
		//page
		String[] pages = { "1", "2", "3", "4", "5", "6", "7" };
		JLabel pageLabel = new JLabel("Page");
		pageLabel.setForeground(Color.WHITE);
		JComboBox<String> dropdown = new JComboBox<>(pages);
		dropdown.setBackground(Color.WHITE);
		dropdown.setPreferredSize(new Dimension(150, dropdown.getPreferredSize().height));

		//tag
		JTextField tagField = new JTextField(15);
		tagField.setBackground(Color.WHITE);
		tagField.setEditable(false);
		JLabel tagLabel = new JLabel("Tag");
		tagLabel.setForeground(Color.WHITE);
		
		//copy to clipboard the hashMD5
		JButton copyButton = new JButton("\u2398");
		copyButton.setToolTipText("Copy to your clipboard");
		Border leftBorder = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
        Border topBorder = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.WHITE);
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE);
        Border rightBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.WHITE);
        Border combinedBorder = BorderFactory.createCompoundBorder(leftBorder, BorderFactory.createCompoundBorder(topBorder, 
                				BorderFactory.createCompoundBorder(bottomBorder, rightBorder)));
		copyButton.setMargin(new Insets(1,1,1,1));
		copyButton.setBorder(combinedBorder);
		copyButton.setBackground(Color.WHITE);
		copyButton.setFocusable(false);

        // Adiciona um ActionListener ao botão
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hashMD5 != null) {
                	StringSelection stringSelection = new StringSelection(hashMD5);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);

                    JOptionPane.showMessageDialog(null, "Texto copiado para o clipboard!");
                }
            }
        });
		
		//figure
		JLabel figureLabel = new JLabel("Figure");
		figureLabel.setForeground(Color.WHITE);
		JTextField imageNameField = new JTextField(30);
		imageNameField.setEditable(false);
		JButton selectBtn = new JButton("...");
		selectBtn.setFocusable(false);
		selectBtn.setBackground(Color.WHITE);
		selectBtn.setBorder(null);
		
		//select figure button
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileFilter() {
					public boolean accept(File file) {
						return file.getName().toLowerCase().endsWith(".png")
							|| file.getName().toLowerCase().endsWith(".jpg")
							|| file.getName().toLowerCase().endsWith(".jpeg") 
							|| file.isDirectory();
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
				int result = fileChooser.showOpenDialog(ViewFigureInfo.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					
					selectedFile = fileChooser.getSelectedFile();
					imageNameField.setText(selectedFile.getName());
					
					ImageIcon icon = new ImageIcon(selectedFile.getPath());
					Image image = icon.getImage().getScaledInstance(220, 250, Image.SCALE_SMOOTH);;
					ImageIcon resizedIcon = new ImageIcon(image);
					label.setIcon(resizedIcon);
					label.setBounds(yy + 180, xx - 100, 220, 250);
					hashMD5 = hashMD5Generator(selectedFile);
					System.out.println(hashMD5);
					tagField.setText(hashMD5);
				}
			}
		});

		//description
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setForeground(Color.WHITE);
		JTextArea descriptionField = new JTextArea(15, 10);
		descriptionField.setLineWrap(true); 
		descriptionField.setWrapStyleWord(true); 
		descriptionField.setCaretPosition(0);
		descriptionField.setBackground(Color.WHITE);
		JScrollPane scrollPaneArea = new JScrollPane(descriptionField);
		scrollPaneArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneArea.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
		
		//ok button
		JButton okayBtn = new JButton("Ok");
		okayBtn.setFocusable(false);
		okayBtn.setBackground(Color.WHITE);
		okayBtn.setForeground(Color.black);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFocusable(false);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setForeground(Color.black);
		label = new JLabel();
		
		//if they brought lf, lets set de info with this lf to edit him
		

		//ok controller
		okayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LittleFigureService lfs = new LittleFigureService();
				LittleFigureRepository lfr = new LittleFigureRepository();
				if(lf == null) {
					LittleFigure lf = new LittleFigure(lfr.getNewId(), nameField.getText(), tagField.getText(),
							convertFileToByteArray(selectedFile), descriptionField.getText(),
							dropdown.getSelectedIndex()+ 1, 3, 0);
					lfs.saveLF(lf);
					JOptionPane.showMessageDialog(null, "Figurinha criada com sucesso"); 
					dialog.dispose();
				}else {
					LittleFigure lff = new LittleFigure(lf.getId(), nameField.getText(), tagField.getText(),
							convertFileToByteArray(selectedFile), descriptionField.getText(),
							dropdown.getSelectedIndex()+ 1, 3, 0);
					lfs.editLFById(lf.getId(), lff);
					JOptionPane.showMessageDialog(null, "Figurinha editada com sucesso"); 
					dialog.dispose();
				}
			}
		});
		
		cancelBtn.addActionListener(e -> dialog.dispose());

		
		//size and coords of the items
		nameLabel.setBounds(yy - 100, xx - 90, 100, 25);
		nameField.setBounds(fieldYY, xx - 90, 200, 25);
		pageLabel.setBounds(yy - 100, xx - 45, 185, 25);
		dropdown.setBounds(fieldYY, xx - 45, 75, 25);
		figureLabel.setBounds(yy - 100, xx - 5, 65, 25);
		imageNameField.setBounds(fieldYY, xx - 5, 153, 25);
		selectBtn.setBounds(yy + 127, xx - 5, 45, 25);
		tagLabel.setBounds(yy - 100, xx + 35, 100, 25);
		tagField.setBounds(fieldYY, xx + 35, 200, 25);
		copyButton.setBounds(yy + 147, xx + 35, 25, 25);
		descriptionLabel.setBounds(yy - 100, xx + 75, 100, 25);
		descriptionField.setBounds(fieldYY, xx + 75, 200, 50);
		scrollPaneArea.setBounds(fieldYY, xx + 75, 200, 50);
		okayBtn.setBounds(fieldYY-10, xx + 175, 100, 25);
		cancelBtn.setBounds(yy + 150, xx + 175, 100, 25);
		
		//setting hover to the buttons
		selectBtn.addMouseListener(hoverButton(selectBtn));
		copyButton.addMouseListener(hoverButton(copyButton));
		cancelBtn.addMouseListener(hoverButton(cancelBtn));
		okayBtn.addMouseListener(hoverButton(okayBtn));
		
		//adding the items in the panel
		panel.setLayout(null);
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(pageLabel);
		panel.add(dropdown);
		panel.add(figureLabel);
		panel.add(imageNameField);
		panel.add(selectBtn);
		panel.add(tagLabel);
		panel.add(copyButton);
		panel.add(tagField);
		panel.add(descriptionLabel);
		panel.add(scrollPaneArea);
		panel.add(okayBtn);
		panel.add(cancelBtn);
		panel.add(label);

		if(lf != null) {
			nameField.setText(lf.getName());
			dropdown.setSelectedIndex(lf.getPage() <= 0 ? 0 : lf.getPage()-1);
			ByteArrayInputStream bais = new ByteArrayInputStream(lf.getPhoto());
			BufferedImage newImage = null;
			try {
				newImage = ImageIO.read(bais);
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			
			Image resizedImage = newImage.getScaledInstance(220,250, Image.SCALE_SMOOTH);
			ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
			label.setIcon(resizedImageIcon);
			label.setBounds(yy + 180, xx - 100, 220, 250);
			tagField.setText(lf.getTag());
			descriptionField.setText(lf.getDescription());
		}
		
		
		dialog.add(panel);
		dialog.setVisible(true);
	}

	private static String hashMD5Generator(File arquivo) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            try (DigestInputStream dis = new DigestInputStream(new FileInputStream(arquivo), md)) {
                byte[] buffer = new byte[1024];
                while (dis.read(buffer) != -1) {
                    // Continua lendo o arquivo para calcular o hash
                }
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
	
	public static MouseAdapter hoverButton(JButton selectBtn) {
		 
        return new MouseAdapter() {
		@Override
        public void mouseEntered(MouseEvent e) {
        	selectBtn.setBackground(new Color(13, 62, 16));
        	selectBtn.setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        	selectBtn.setBackground(Color.WHITE);
        	selectBtn.setForeground(Color.BLACK);
        }
      };
	}

	public static byte[] convertFileToByteArray(File file)  {
		try (FileInputStream fis = new FileInputStream(file); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			return bos.toByteArray();
		} catch (IOException e) {
			e.getMessage();
			return null;
		}
	}

//	public static void main(String args[]) {
//		SwingUtilities.invokeLater(() -> {
//			LittleFigure lf = null;
//			ViewFigureInfo vf = new ViewFigureInfo(lf);
//				vf.openDialog(null);
//		});
//
//	}

}