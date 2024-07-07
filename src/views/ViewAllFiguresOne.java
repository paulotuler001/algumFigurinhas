package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import services.LittleFigureService;

		public class ViewAllFiguresOne extends JFrame {

		    public void openDialog(JFrame parentFrame) {

		    	this.setTitle("All Figures Frame");		        
		    	this.setSize(new Dimension(800, 600));
		    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		        this.setLocationRelativeTo(null);
		    	this.setLayout(new BorderLayout());
		               
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
		        aboutBtn.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		ViewSobre vs = new ViewSobre();
		        		vs.setVisible(true);
		        	}
		        });
		        JPanel topButtonsPanel = new JPanel();
		        topButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		        topButtonsPanel.add(changePasswordBtn);
		        topButtonsPanel.add(addnewFigureBtn);
		        topButtonsPanel.add(aboutBtn);
		        topButtonsPanel.setBackground((new Color(13, 62, 16)));

		        JPanel figurineSlotsPanel = new JPanel();
		        figurineSlotsPanel.setLayout(new GridLayout(2, 4, 10, 10)); 
		        figurineSlotsPanel.setBackground(new Color(13, 62, 16)); 

		        LittleFigureService lfs = new LittleFigureService();
		        ArrayList<ImageIcon> figures = lfs.getAllPhotos();
		        
		        for (int i = 0; i< 8; i++) { 
		            JPanel slotPanel = new JPanel();
		            slotPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		            slotPanel.setBackground((new Color(162, 219, 118)));
		            slotPanel.setPreferredSize(new Dimension(100, 100)); 
		            JLabel figurineLabel = new JLabel("Figurinha " + (i + 1));
		            try {
		            	ImageIcon icon = figures.get(i);
		            	Image image = icon.getImage().getScaledInstance(240, 260, Image.SCALE_SMOOTH);;
		            	ImageIcon resizedIcon = new ImageIcon(image);
		            	figurineLabel.setIcon(resizedIcon != null? resizedIcon : null);
		            }catch(IndexOutOfBoundsException e) {
		            	e.getMessage();
		            }
		            slotPanel.add(figurineLabel);
		            figurineSlotsPanel.add(slotPanel);
		        }

		        JPanel pageControlsPanel = new JPanel();
		        pageControlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		        pageControlsPanel.setBackground((new Color(13, 62, 16)));
		        JButton firstPageBtn = new JButton("<<");
		        JButton prevPageBtn = new JButton("<");
		        JButton nextPageBtn = new JButton(">");
		        JButton lastPageBtn = new JButton(">>");
		        JLabel pageLabel = new JLabel("Página 2 de 10 ");
		        pageLabel.setForeground(Color.WHITE);
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
		        
		        prevPageBtn.addActionListener(e-> this.dispose());
		        firstPageBtn.addActionListener(e-> this.dispose());
		        
		        JPanel mainPanel = new JPanel(new BorderLayout());
		        mainPanel.add(topButtonsPanel, BorderLayout.NORTH);
		        mainPanel.add(figurineSlotsPanel, BorderLayout.CENTER);
		        mainPanel.add(pageControlsPanel, BorderLayout.SOUTH);
		        this.add(mainPanel, BorderLayout.CENTER);
		        this.setVisible(true);
		    }
		}
