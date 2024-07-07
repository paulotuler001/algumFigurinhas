package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import configuration.MusicPlayer;
import entities.Album;
import entities.LittleFigure;
import entities.User;
import services.AlbumService;
import services.LittleFigureService;

public class ViewAuthor extends JFrame {

	boolean vol = false;
	private File arquivoSelecionado;
	public ViewAuthor() {
		setTitle("Author Frame");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		 setResizable(false);

		int xx = this.getHeight() / 3;
		int yy = this.getWidth() / 3;
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.png"));
		setIconImage(icon.getImage());
		
		String imagePath = "/images/5.jpg";
		ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource(imagePath));
		Image backgroundImage = backgroundImageIcon.getImage();

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		AlbumService ass = new AlbumService();
		Album pre = ass.getAlbumById(1);
		
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setForeground(Color.WHITE);
		JTextField nameField = new JTextField(15);
		nameField.setBackground(Color.WHITE);

		JLabel pageLabel = new JLabel("Pages");
		pageLabel.setForeground(Color.WHITE);
		JTextField pageField = new JTextField(15);
		pageField.setBackground(Color.WHITE);

		JLabel coverLabel = new JLabel("Cape");
		coverLabel.setForeground(Color.WHITE);

		JTextField campoArquivo = new JTextField(30);
		campoArquivo.setEditable(false);
		JButton btnSelecionar = new JButton("...");
		btnSelecionar.setFocusable(false);
		btnSelecionar.setBackground(Color.WHITE);
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
					arquivoSelecionado = fileChooser.getSelectedFile();
					campoArquivo.setText(arquivoSelecionado.getName());
				}
			}
		});
		
		if(pre!=null) {
			nameField.setText(pre.getName());
			pageField.setText(pre.getPages().toString());
			campoArquivo.setText("cape.jpg");
		}

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
		removeFigureAllBtn.setToolTipText("Erase All");
		removeFigureAllBtn.setFocusable(false);
		removeFigureAllBtn.setBackground(Color.white);
		JTextField filterFigureField = new JTextField(15);
		JButton backBtn = new JButton("\u2190");
		backBtn.setToolTipText("Log Out");
		backBtn.addActionListener(e -> dispose());
		backBtn.setMargin(new Insets(3,3,3,3));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.DARK_GRAY);
		backBtn.setFont(new Font("", Font.BOLD, 20));
		backBtn.setForeground(Color.WHITE);
		
		JButton saveAlbum = new JButton("S");
		saveAlbum.setToolTipText("Save Album");
		saveAlbum.setBackground(Color.WHITE);
		
		saveAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlbumService as = new AlbumService();
				Album album = new Album(1, nameField.getText(), Integer.parseInt(pageField.getText()), convertFileToByteArray(arquivoSelecionado));
				if(as.getAlbumById(1) != null) {
					as.editAlbum(album);
				}else {
					as.saveAlbum(album);
				}
			}
		});

		LittleFigureService lfs = new LittleFigureService();

		String[] columns = { "#", "Name", "Page" };
		DefaultTableModel model = new DefaultTableModel(lfs.getAllLittleFigures(), columns);
		JTable tabela = new JTable(model);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(10);

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.getViewport().setBackground(new Color(13, 62, 16));
		scrollPane.setBorder(new EmptyBorder(15, 5, 5, 5));
		scrollPane.setBackground(Color.DARK_GRAY);

		JLabel figure = new JLabel("Figures");

		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setBackground(new Color(162, 219, 118));
		tabela.getTableHeader().setBackground(Color.DARK_GRAY);
		tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tabela.getTableHeader().setForeground(Color.WHITE);

		removeFigureBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int selectedRow = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
				System.out.println(selectedRow);
				if (selectedRow != -1) {
					System.out.println(selectedRow);
					lfs.deleteLFById(selectedRow);
					model.removeRow(tabela.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um usuário para apagar.");
				}
			}
		});
		JFrame selFrame = this;

		addFigureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LittleFigure lf = null;
				ViewFigureInfo vfi = new ViewFigureInfo(lf);
				vfi.openDialog(selFrame);
				dispose();
				ViewAuthor authorRefreshed = new ViewAuthor();
				authorRefreshed.setVisible(true);
			}
		});
		
		removeFigureAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = lfs.deleteAllLFs();
				
				if(response == 1){
					Object a = model.getValueAt(0, 0);
				while(model.getRowCount() > 0){
					try{
						model.removeRow(0);
					}catch(ArrayIndexOutOfBoundsException e1){
						e1.getMessage();
					}
				}
				dispose();
				ViewAuthor authorRefreshed = new ViewAuthor();
				authorRefreshed.setVisible(true);
			}
			}
		});
		
		editFigureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String selectedRow = String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0));
				Integer row = Integer.parseInt(selectedRow);
				LittleFigure figure = lfs.getLFById(row);
				
				System.out.println(figure.getName());
				ViewFigureInfo vfi = new ViewFigureInfo(figure);
				vfi.openDialog(selFrame);
				
				dispose();
				ViewAuthor va = new ViewAuthor();
				va.setVisible(true);
				
			}
		});
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        tabela.setRowSorter(sorter);
		filterFigureField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String text = filterFigureField.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null); 
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } catch (PatternSyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
		
		int btnYY = xx+20;
		
		nameLabel.setBounds(yy - 100, xx - 90, 185, 25);
		nameField.setBounds(yy - 50, xx - 90, 415, 25);
		pageLabel.setBounds(yy - 100, xx - 50, 185, 25);
		pageField.setBounds(yy - 50, xx - 50, 75, 25);
		coverLabel.setBounds(yy + 50, xx - 50, 65, 25);
		campoArquivo.setBounds(yy + 90, xx - 50, 230, 25);
		btnSelecionar.setBounds(yy + 320, xx - 50, 45, 25);
		
		addFigureBtn.setBounds(yy - 100, btnYY, 50, 25);
		removeFigureBtn.setBounds(yy - 45, btnYY, 50, 25);
		editFigureBtn.setBounds(yy + 10, btnYY, 50, 25);
		filterFigureField.setBounds(yy + 68, btnYY, 150, 25);
		filterFigureBtn.setBounds(yy + 220, btnYY, 50, 25);
		removeFigureAllBtn.setBounds(yy + 275, btnYY, 50, 25);
		scrollPane.setBounds(yy - 110, xx + 70, 500, 200);
		backBtn.setBounds(0, 0, 35, 35);
		saveAlbum.setBounds(yy+330, btnYY, 50,25);

		figure.setBounds(yy - 120, -233, 1000, 1000);
		figure.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		figure.setForeground(Color.white);
		panel.setLayout(null);
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
		panel.add(figure);
		panel.add(scrollPane);
		panel.add(backBtn);
		panel.add(saveAlbum);

		add(panel);
	}

	public static void main(String args[]) {
		ViewAuthor va = new ViewAuthor();
		va.setVisible(true);

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

}
