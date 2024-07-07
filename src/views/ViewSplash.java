package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewSplash extends JDialog {

	private JDialog dialog = new JDialog();
	
	public void openDialog() {
		
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Random random = new Random();
		
		int randomNumber = random.nextInt(0, 7);
		
		ArrayList<ImageIcon> icon = new ArrayList<>();
		
		
		ImageIcon a = new ImageIcon(getClass().getResource("/images/a.jpg"));
		ImageIcon b = new ImageIcon(getClass().getResource("/images/b.jpg"));
		ImageIcon c = new ImageIcon(getClass().getResource("/images/c.png"));
		ImageIcon d = new ImageIcon(getClass().getResource("/images/d.png"));
		ImageIcon e = new ImageIcon(getClass().getResource("/images/e.jpg"));
		ImageIcon f = new ImageIcon(getClass().getResource("/images/f.jpg"));
		ImageIcon g = new ImageIcon(getClass().getResource("/images/g.jpg"));
		ImageIcon h = new ImageIcon(getClass().getResource("/images/h.jpg"));
		
		
		icon.add(a);
		icon.add(b);
		icon.add(c);
		icon.add(d);
		icon.add(e);
		icon.add(f);
		icon.add(g);
		icon.add(h);
		Image iconImage = icon.get(randomNumber).getImage();
		dialog.setIconImage(iconImage);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setIcon(icon.get(randomNumber));
		JLabel text = new JLabel();
		
		dialog.setSize(new Dimension(800, 600));
		dialog.setLocationRelativeTo(null);
		dialog.setLayout(new BorderLayout());
		switch(randomNumber+1) {
		case 1: 
			text.setText("<html><div style='text-align: center;'>Atenção: Lavagem de dinheiro é recomendado de 9 entre 10<br> profissionais de contabilidade<div/></html>" );
			break;
		case 2:
			text.setText("<html><div style='text-align: center;'>Nota: Escolha suas figurinhas com cuidado, não compre<br>figurinhas repetidas!<div/></html>");
			break;
		case 3: 
			text.setText("<html><div style='text-align: center;'>Atenção: Qualquer atividade apresentada aqui não é<br>recomendada para menores de 18 anos<div/></html>");
			break;
		case 4:
			text.setText("<html><div style='text-align: center;'>Breaking Bad: The Ultimate Album<div/></html>");
			break;
		case 5: 
			text.setText("<html><div style='text-align: center;'>Estamos de olho em você<div/></html>");
			break;
		case 6:
			text.setText("<html><div style='text-align: center;'>Breaking Bad: The Ultimate Album<div/></html>");
			break;
		case 7: 
			text.setText("<html><div style='text-align: center;'>Está em um super esquema de metanfetamina?<br> Melhor chamar Saul!<div/></html>");
			break;
		case 8:
			text.setText("<html><div style='text-align: center;'>Breaking Bad: The Ultimate Album<div/></html>");
			break;
		
		}
		
		text.setFont(new Font("Courier New", Font.BOLD, 20));
		text.setPreferredSize(new Dimension(100, 100));
		
		JPanel loading = new JPanel();
		loading.setBackground(new Color(13, 62, 16));
		loading.setPreferredSize(new Dimension(20,20));
		
		label.setBounds(200,25,400,400);
		text.setBounds(10,400,800,100);
		
		
		panel.setLayout(null);
		panel.add(label);
		panel.add(text);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		text.setHorizontalAlignment(SwingConstants.CENTER);

//		int[] arr = new int[100];
//		
//		arr[0] = 0;
//		arr[1] = 1;
//		arr[2] = 1;
//		for(int i = 3; i < 100;i++) {
//		  arr[i] = arr[i-1] + arr[i-2];
//		  loading.setBounds(0,550,arr[i],30);
//		  panel.add(loading);
//		  try {
//				Thread.sleep(400);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//		  dialog.add(panel);
//		  dialog.setVisible(true);
//		} //mockado em 400
//		for(int i = 0; i <= 800;i= i+100) {
//			
//			  loading.setBounds(0,550,i,30);
//			  panel.add(loading);
//			  try {
//					Thread.sleep(400);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//			  dialog.add(panel);
//			  dialog.setVisible(true);
//			} //de 100 em 100
		
//		for(int i = 0; i <= 800;i = i+5) {
//			
//			  loading.setBounds(0,550,i,30);
//			  panel.add(loading);
//			  try {
//					Thread.sleep(4);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//			  dialog.add(panel);
//			  dialog.setVisible(true);
//			} //de 5 em 5 rapido
		
int[] arr = new int[100];
		
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 1;
		for(int i = 3; i < 17;i++) {
		  arr[i] = arr[i-1] + arr[i-2];
		  loading.setBounds(0,550,arr[i],30);
		  panel.add(loading);
		  try {
				Thread.sleep(arr[i] * 5);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		  dialog.add(panel);
		  dialog.setVisible(true);
		} //o mais foda
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		dialog.dispose();
	}
	public static void main(String args[]) {
		ViewSplash vs = new ViewSplash();
		vs.openDialog();
	}
}


