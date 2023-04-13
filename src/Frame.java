import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    GamePanel gamePanel = new GamePanel();
    InterfacePanel interfacePanel = new InterfacePanel(gamePanel);

    int FRAME_WIDTH = gamePanel.getPANEL_WIDTH();
    int FRAME_HEIGHT = gamePanel.getPANEL_HEIGHT();

    Frame(){
        this.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);  // Dzięki temu można zmienić kolor Bara
        this.setTitle("Snake");
        this.setDefaultCloseOperation(3);
        this.setBackground(Color.darkGray);
        this.setSize(660,753);
        this.setLayout(null);
        this.add(gamePanel);
        this.add(interfacePanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}