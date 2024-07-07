package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewAlbum extends JFrame {

    public ViewAlbum() {
        setTitle("Album Frame");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Botões no canto superior esquerdo
        JButton changePasswordBtn = new JButton("1");
        changePasswordBtn.setFocusable(false);
        changePasswordBtn.setBackground(Color.white);
        changePasswordBtn.setToolTipText("Change Password");

        JButton addnewFigureBtn = new JButton("2");
        addnewFigureBtn.setFocusable(false);
        addnewFigureBtn.setBackground(Color.white);
        addnewFigureBtn.setToolTipText("Add new figure");

        JButton aboutBtn = new JButton("3");
        aboutBtn.setFocusable(false);
        aboutBtn.setBackground(Color.white);
        aboutBtn.setToolTipText("About");

        // JPanel para os botões no canto superior esquerdo
        JPanel topButtonsPanel = new JPanel();
        topButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topButtonsPanel.setBackground(new Color(13, 62, 16)); // Define a cor de fundo
        topButtonsPanel.add(changePasswordBtn);
        topButtonsPanel.add(addnewFigureBtn);
        topButtonsPanel.add(aboutBtn);

        // JPanel para exibir a foto da capa
        String imagePath = "/images/serieTitle.jpg"; // Caminho da imagem da capa
        ImageIcon coverImageIcon = new ImageIcon(getClass().getResource(imagePath));
        JLabel coverImageLabel = new JLabel(coverImageIcon);


        // Controles de página
        JPanel pageControlsPanel = new JPanel();
        pageControlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pageControlsPanel.setBackground(new Color(13, 62, 16)); // Define a cor de fundo
        JButton firstPageBtn = new JButton("<<");
        JButton prevPageBtn = new JButton("<");
        JButton nextPageBtn = new JButton(">");
        JButton lastPageBtn = new JButton(">>");
        JLabel pageLabel = new JLabel("Página 1 de 10 ");
        pageLabel.setForeground(Color.WHITE); // Define a cor do texto
        pageControlsPanel.add(firstPageBtn);
        firstPageBtn.setBackground(Color.white);
        firstPageBtn.setFocusable(false);
        pageControlsPanel.add(prevPageBtn);
        prevPageBtn.setBackground(Color.white);
        prevPageBtn.setFocusable(false);
        pageControlsPanel.add(pageLabel);
        pageControlsPanel.add(nextPageBtn);
        nextPageBtn.setBackground(Color.white);
        nextPageBtn.setFocusable(false);
        pageControlsPanel.add(lastPageBtn);
        lastPageBtn.setBackground(Color.white);
        lastPageBtn.setFocusable(false);
        
        JFrame selFrame = this;
        
        nextPageBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ViewAllFiguresOne vaf = new ViewAllFiguresOne();
        		vaf.openDialog(selFrame);
        	}
        });

        // JPanel principal que contém todos os componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topButtonsPanel, BorderLayout.NORTH);
        mainPanel.add(coverImageLabel, BorderLayout.CENTER);
        mainPanel.add(pageControlsPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            ViewAlbum va = new ViewAlbum();
            va.setVisible(true);
        });
    }
}
