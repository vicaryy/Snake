import java.awt.*;
import java.util.Random;

public class UI {
    GamePanel gamePanel;
    Random rd = new Random();

    UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void drawBackground(Graphics2D g2d) {
        if(gamePanel.quickMode){
            drawBackgroundQuickMode(g2d);
        }
        else if(gamePanel.defaultMode || gamePanel.invisibleWallMode || gamePanel.immortalMode) {
            g2d.setPaint(gamePanel.backgroundColor1);
            g2d.fillRect(30, 10, gamePanel.getPANEL_WIDTH(), gamePanel.getPANEL_HEIGHT());
            for (int x = 15; x < (gamePanel.getPANEL_WIDTH()) / 2; x += gamePanel.getFRAME_SIZE()) {
                for (int y = 5; y < (gamePanel.getPANEL_HEIGHT() - 10) / 2; y += gamePanel.getFRAME_SIZE()) {
                    g2d.setPaint(gamePanel.backgroundColor2);
                    g2d.fillRect(x * 2, y * 2, gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE());
                    g2d.fillRect(x * 2 + gamePanel.getFRAME_SIZE(), y * 2 + gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE());
                }
            }
        }
        g2d.setPaint(Color.darkGray);
        g2d.fillRect(0, 610, 660, 60);
        g2d.fillRect(630, 0, 60, 660);
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(gamePanel.borderColor);
        g2d.drawRect(25, 5, gamePanel.getPANEL_WIDTH() + 10, gamePanel.getPANEL_HEIGHT() + 10);

        drawArc(g2d);
    }

    public void drawLock(Graphics2D g2d){
        if (gamePanel.invisibleWallMode) {
            g2d.drawImage(gamePanel.lockImage, 319, 613, gamePanel);
        } else {
            g2d.drawImage(gamePanel.lockImage, 315, 613, gamePanel);
        }
    }

    public void drawArc(Graphics2D g2d){
        g2d.drawArc(291, 575, 80, 80, 180, 180);
        g2d.setPaint(Color.darkGray);
        g2d.drawLine(296, 615, 366, 615);
    }

    public void drawSnake(Graphics2D g2d) {
        if (gamePanel.immortalMode) {
            drawSnakeImmortalMode(g2d);
        } else {
            for (int i = 1; i < gamePanel.getSNAKE_LENGTH(); i++) {
                g2d.setPaint(gamePanel.snakeBodyColor);
                g2d.fillRect(gamePanel.snake_x[i]+2, gamePanel.snake_y[i]+2, gamePanel.getFRAME_SIZE()-4, gamePanel.getFRAME_SIZE()-4);
                g2d.setStroke(new BasicStroke(2));
                g2d.setPaint(Color.BLACK);
                g2d.drawRect(gamePanel.snake_x[i]+2, gamePanel.snake_y[i]+2, gamePanel.getFRAME_SIZE()-4, gamePanel.getFRAME_SIZE()-4);
            }
        }
            g2d.drawImage(gamePanel.snakeHeadDirection(), gamePanel.snake_x[0], gamePanel.snake_y[0], gamePanel);
    }

    public void drawBackgroundQuickMode(Graphics2D g2d){
        g2d.setPaint(gamePanel.backgroundColor1);
        g2d.fillRect(30, 10, gamePanel.getPANEL_WIDTH(), gamePanel.getPANEL_HEIGHT());
        for (int x = 15; x < (gamePanel.getPANEL_WIDTH()) / 2; x += gamePanel.getFRAME_SIZE()) {
            for (int y = 5; y < (gamePanel.getPANEL_HEIGHT() - 10) / 2; y += gamePanel.getFRAME_SIZE()) {
                g2d.setPaint(gamePanel.backgroundColor2);
                g2d.fillRect(x * 2, y * 2, gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE());
                g2d.fillRect(x * 2 + gamePanel.getFRAME_SIZE(), y * 2 + gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE());
            }
        }
    }

    public void drawSnakeImmortalMode(Graphics2D g2d){
        for (int i = 1; i < gamePanel.getSNAKE_LENGTH(); i++) {
            g2d.setPaint(new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255)));
            g2d.fillRect(gamePanel.snake_x[i], gamePanel.snake_y[i], gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE());
            g2d.setStroke(new BasicStroke(2));
            g2d.setPaint(Color.BLACK);
            g2d.drawRect(gamePanel.snake_x[i], gamePanel.snake_y[i], gamePanel.getFRAME_SIZE(), gamePanel.getFRAME_SIZE());
            g2d.setPaint(new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255)));
            g2d.drawRect(25, 5, gamePanel.getPANEL_WIDTH() + 10, gamePanel.getPANEL_HEIGHT() + 10);
            g2d.drawArc(291, 575, 80, 80, 180, 180);
            g2d.setPaint(Color.darkGray);
            g2d.drawLine(296, 615, 366, 615);
            drawLock(g2d);
        }
    }

    public void drawApple(Graphics2D g2d) {
        g2d.drawImage(gamePanel.appleImage, gamePanel.apple_x - 5, gamePanel.apple_y - 7, gamePanel);
        if(gamePanel.immortalMode){
            g2d.drawImage(gamePanel.appleImage, gamePanel.apple_x1 - 5, gamePanel.apple_y1 - 7, gamePanel);
            g2d.drawImage(gamePanel.appleImage, gamePanel.apple_x2 - 5, gamePanel.apple_y2 - 7, gamePanel);
            g2d.drawImage(gamePanel.appleImage, gamePanel.apple_x3 - 5, gamePanel.apple_y3 - 7, gamePanel);
        }
    }

    public void drawQuickModeObject(Graphics2D g2d) {
        if(!gamePanel.objectCreated) {
            gamePanel.newModeObject();
            gamePanel.objectCreated = true;
        }
        gamePanel.modes.modeImageSetter();
        g2d.drawImage(gamePanel.modeImage, gamePanel.modeObject_x -5, gamePanel.modeObject_y -5, gamePanel);
    }

    public void drawInvisibleWallObject(Graphics2D g2d) {
        if (!gamePanel.objectCreated) {
            gamePanel.newModeObject();
            gamePanel.objectCreated = true;
        }
        gamePanel.modes.modeImageSetter();
        g2d.drawImage(gamePanel.modeImage, gamePanel.modeObject_x - 5, gamePanel.modeObject_y - 5, gamePanel);
    }
    public void drawImmortalModeObject(Graphics2D g2d) {
        if(!gamePanel.objectCreated) {
            gamePanel.newModeObject();
            gamePanel.objectCreated = true;
        }
        gamePanel.modes.modeImageSetter();
        g2d.drawImage(gamePanel.modeImage, gamePanel.modeObject_x -5, gamePanel.modeObject_y -5, gamePanel);
    }
}
