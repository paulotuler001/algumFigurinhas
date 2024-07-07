package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.JWindow;

public class ViewSplash extends JWindow {
    private JProgressBar progressBar;
    private int count = 0;
    private Timer timer;

    public ViewSplash() {
        JPanel content = new JPanel(new BorderLayout());
        setContentPane(content);

        Random random = new Random();
        int randomNumber = random.nextInt(8);
        ArrayList<ImageIcon> icons = new ArrayList<>();
        icons.add(new ImageIcon(getClass().getResource("/images/money.jpg")));
        icons.add(new ImageIcon(getClass().getResource("/images/lab.png")));
        icons.add(new ImageIcon(getClass().getResource("/images/epOne.jpg")));
        icons.add(new ImageIcon(getClass().getResource("/images/coverLeadMen.jpg")));
        icons.add(new ImageIcon(getClass().getResource("/images/e.jpg")));
        icons.add(new ImageIcon(getClass().getResource("/images/gunCollage.png")));
        icons.add(new ImageIcon(getClass().getResource("/images/SaulCommercial.jpg")));
        icons.add(new ImageIcon(getClass().getResource("/images/h.jpg")));

        ImageIcon selectedIcon = icons.get(randomNumber);
        JLabel label = new JLabel(selectedIcon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.BOTTOM);
        content.add(label, BorderLayout.NORTH);

        JLabel text = new JLabel();
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("Courier New", Font.BOLD, 20));
        String[] texts = {
            "<html><div style='text-align: center;'>Atenção: Lavagem de dinheiro é recomendado de 9 entre 10<br> profissionais de contabilidade</div></html>",
            "<html><div style='text-align: center;'>Nota: Escolha suas figurinhas com cuidado, não compre<br>figurinhas repetidas!</div></html>",
            "<html><div style='text-align: center;'>Atenção: Qualquer atividade apresentada aqui não é<br>recomendada para menores de 18 anos</div></html>",
            "<html><div style='text-align: center;'>Breaking Bad: The Ultimate Album</div></html>",
            "<html><div style='text-align: center;'>Estamos de olho em você</div></html>",
            "<html><div style='text-align: center;'>Breaking Bad: The Ultimate Album</div></html>",
            "<html><div style='text-align: center;'>Está em um super esquema de metanfetamina?<br> Melhor chamar Saul!</div></html>",
            "<html><div style='text-align: center;'>Breaking Bad: The Ultimate Album</div></html>"
        };
        text.setText(texts[randomNumber]);
        content.add(text, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(800, 30));
        progressBar.setStringPainted(false);
        progressBar.setBackground(new Color(13, 62, 16));
        content.add(progressBar, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public void showSplash() {
        setVisible(true);

        timer = new Timer(50, e -> {
            if (count < 100) {
                progressBar.setValue(count);
                count++;
            } else {
                timer.stop();
                setVisible(false);
                dispose();
            }
        });
        timer.start();
    }
    
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
            ViewSplash splash = new ViewSplash();
            splash.showSplash();
		});
    }
}
