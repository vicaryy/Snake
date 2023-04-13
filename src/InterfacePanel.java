import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class InterfacePanel extends JPanel implements ActionListener {
    int PANEL_WIDTH = 660;
    int PANEL_HEIGHT = 65;
    boolean defaultM;
    boolean quickM;
    boolean invisibleM;
    boolean immortalM;
    Timer timer;
    GamePanel gamePanel;
    JLabel imageProgressBar;
    JProgressBar progressBar;
    ImageIcon modeLogo;
    Image heart;
    Image heartEmpty;
    Random rd;

    InterfacePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setVisible(true);
        progressBar.setBounds(508, 24, 130, 20);
        progressBar.setBackground(new Color(30, 50, 50));
        progressBar.setForeground(new Color(0, 150, 250));
        progressBar.setValue(progressBar.getMaximum());
        progressBar.setBorderPainted(false);

        imageProgressBar = new JLabel();
        imageProgressBar.setBounds(473, 20, 30, 30);

        heart = new ImageIcon("images/interface_panel/heart.png").getImage();
        heartEmpty = new ImageIcon("images/interface_panel/empty_heart.png").getImage();

        rd = new Random();


        //PANEL
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.darkGray);
        this.add(progressBar);
        this.add(imageProgressBar);
        this.setVisible(true);
        timer = new Timer(100, this);
        timer.start();
    }

    public void progressBar() {
        if (gamePanel.invisibleWallMode) {
            progressBar.setValue(progressBar.getValue() - 1);
        }
        else if (gamePanel.immortalMode) {
            progressBar.setValue(progressBar.getValue() - 1);
            progressBar.setForeground(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
        }
        else if (gamePanel.quickMode) {

        }
    }

    public void checkMode(){
        if (gamePanel.defaultMode && !defaultM){
            imageProgressBar.setVisible(false);
            progressBar.setVisible(false);
            modesBooleanSetter();
            defaultM = true;
        }
        else if (gamePanel.invisibleWallMode && !invisibleM) {
            progressBar.setMaximum(200);
            progressBar.setValue(progressBar.getMaximum());
            progressBar.setVisible(true);
            imageProgressBar.setVisible(true);
            progressBar.setForeground(new Color(0, 150, 250));
            modeLogo = new ImageIcon("images/interface_panel/key_logo.png");
            imageProgressBar.setIcon(modeLogo);
            modesBooleanSetter();
            invisibleM = true;
        }
        else if (gamePanel.immortalMode && !immortalM) {
            progressBar.setMaximum(100);
            progressBar.setValue(progressBar.getMaximum());
            progressBar.setVisible(true);
            imageProgressBar.setVisible(true);
            modeLogo = new ImageIcon("images/interface_panel/banana_logo.png");
            imageProgressBar.setIcon(modeLogo);
            modesBooleanSetter();
            immortalM = true;
        }
        else if (gamePanel.quickMode && !quickM) {
            progressBar.setValue(progressBar.getMaximum());
            progressBar.setVisible(true);
            progressBar.setForeground(Color.red);
            imageProgressBar.setVisible(true);
            progressBar.setForeground(new Color(185, 0, 0));
            modeLogo = new ImageIcon("images/interface_panel/pentagram_logo.png");
            imageProgressBar.setIcon(modeLogo);
            modesBooleanSetter();
            quickM = true;
        }
    }

    public void modesBooleanSetter(){
        defaultM = false;
        invisibleM = false;
        immortalM = false;
        quickM = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(new Color(130, 200, 200));
        g2d.setFont(new Font("Monaco", 2, 30));
        g2d.drawString("Score: " + gamePanel.SCORE, 20, 44);

        int x = 247;
        for (int i = 0; i < 3; i++) {
            g2d.drawImage(heartEmpty, x, 5, this);
            x += 55;
        }
        x = 247;
        for (int i = gamePanel.health; i > 0; i--) {
            g2d.drawImage(heart, x, 5, this);
            x += 55;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkMode();
        progressBar();
        repaint();
    }
}
