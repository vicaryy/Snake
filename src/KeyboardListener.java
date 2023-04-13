import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class KeyboardListener implements KeyListener {
    GamePanel gamePanel;
    Timer timer;
    long lastTimePressedKey = 0;
    long currentTime;

    KeyboardListener(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        timer = new Timer(1,e->{
            currentTime = System.currentTimeMillis();
        });
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Delay miedzy wcisnieciami: " + (currentTime - lastTimePressedKey));
        if (currentTime - lastTimePressedKey > gamePanel.buttonsDelay) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (gamePanel.direction != 'L') {
                    gamePanel.direction = 'R';
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (gamePanel.direction != 'R') {
                    gamePanel.direction = 'L';
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (gamePanel.direction != 'D') {
                    gamePanel.direction = 'U';
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (gamePanel.direction != 'U') {
                    gamePanel.direction = 'D';
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
            }
            lastTimePressedKey = System.currentTimeMillis();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

