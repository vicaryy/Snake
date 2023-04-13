import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Modes {

    GamePanel gamePanel;
    long invisibleModeTime;
    long immortalModeTime;

    Modes(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void defaultMode(){
        Random rd = new Random();
        modesBooleanSetter();
        gamePanel.setDefaultMode(true);
        gamePanel.setDrawnModeNumber(rd.nextInt(0,1));
        gamePanel.setGameSpeed(130);
        gamePanel.timer.setDelay(130);
        System.out.println("Default Mode / ON");
        gamePanel.setButtonsDelay(120);
        gamePanel.setBackgroundColor1(new Color(0, 135, 20));
        gamePanel.setBackgroundColor2(new Color(0, 125, 20));
        gamePanel.setBorderColor(new Color(255,0,0));
        gamePanel.setSnakeBodyColor(new Color(200,200,30));
        gamePanel.setAppleImage(new ImageIcon("images/default_mode/apple_image.png").getImage());
        gamePanel.setSnakeHeadImageR(new ImageIcon("images/default_mode/default_R.png").getImage());
        gamePanel.setSnakeHeadImageL(new ImageIcon("images/default_mode/default_L.png").getImage());
        gamePanel.setSnakeHeadImageU(new ImageIcon("images/default_mode/default_U.png").getImage());
        gamePanel.setSnakeHeadImageD(new ImageIcon("images/default_mode/default_D.png").getImage());
        gamePanel.setSnakeHeadOpenImageR(new ImageIcon("images/default_mode/defaultOpen_R.png").getImage());
        gamePanel.setSnakeHeadOpenImageL(new ImageIcon("images/default_mode/defaultOpen_L.png").getImage());
        gamePanel.setSnakeHeadOpenImageU(new ImageIcon("images/default_mode/defaultOpen_U.png").getImage());
        gamePanel.setSnakeHeadOpenImageD(new ImageIcon("images/default_mode/defaultOpen_D.png").getImage());
        gamePanel.setLockImage(new ImageIcon("images/default_mode/lock.png").getImage());
    }

    public void quickMode(){
        modesBooleanSetter();
        gamePanel.setQuickMode(true);
        gamePanel.timer.setDelay(45);
        System.out.println("Quick Mode / ON");
        gamePanel.setButtonsDelay(35);
        gamePanel.setBackgroundColor1(new Color(135, 0, 0));
        gamePanel.setBackgroundColor2(new Color(115, 0, 0));
        gamePanel.setSnakeBodyColor(new Color(30, 30, 30));
        gamePanel.setAppleImage(new ImageIcon("images/quick_mode/quick_mode_apple.png").getImage());
        gamePanel.setSnakeHeadImageR(new ImageIcon("images/quick_mode/quick_R.png").getImage());
        gamePanel.setSnakeHeadImageL(new ImageIcon("images/quick_mode/quick_L.png").getImage());
        gamePanel.setSnakeHeadImageU(new ImageIcon("images/quick_mode/quick_U.png").getImage());
        gamePanel.setSnakeHeadImageD(new ImageIcon("images/quick_mode/quick_D.png").getImage());
        gamePanel.setSnakeHeadOpenImageR(new ImageIcon("images/quick_mode/quickOpen_R.png").getImage());
        gamePanel.setSnakeHeadOpenImageL(new ImageIcon("images/quick_mode/quickOpen_L.png").getImage());
        gamePanel.setSnakeHeadOpenImageU(new ImageIcon("images/quick_mode/quickOpen_U.png").getImage());
        gamePanel.setSnakeHeadOpenImageD(new ImageIcon("images/quick_mode/quickOpen_D.png").getImage());
    }

    public void immortalMode(){
        modesBooleanSetter();
        gamePanel.setImmortalMode(true);
        gamePanel.newMultipleApple(1);
        gamePanel.newMultipleApple(2);
        gamePanel.newMultipleApple(3);
        gamePanel.timer.setDelay(80);
        immortalModeTime = System.currentTimeMillis();
        System.out.println("Immortal Mode / ON");
        gamePanel.setButtonsDelay(30);
        gamePanel.setBackgroundColor1(new Color(20, 20, 30));
        gamePanel.setBackgroundColor2(new Color(20, 20, 60));
        gamePanel.setBorderColor(new Color(255,0,0));
        gamePanel.setSnakeBodyColor(new Color(200,200,30));
        gamePanel.setAppleImage(new ImageIcon("images/immortal_mode/banana_image.png").getImage());
        gamePanel.setSnakeHeadImageR(new ImageIcon("images/immortal_mode/immortal_R.png").getImage());
        gamePanel.setSnakeHeadImageL(new ImageIcon("images/immortal_mode/immortal_L.png").getImage());
        gamePanel.setSnakeHeadImageU(new ImageIcon("images/immortal_mode/immortal_U.png").getImage());
        gamePanel.setSnakeHeadImageD(new ImageIcon("images/immortal_mode/immortal_D.png").getImage());
        gamePanel.setLockImage(new ImageIcon("images/default_mode/lock.png").getImage());
    }

    public void invisibleWallMode(){
        modesBooleanSetter();
        gamePanel.setInvisibleWallMode(true);
        invisibleModeTime = System.currentTimeMillis();
        gamePanel.timer.setDelay(100);
        System.out.println("Invisible Mode / ON");
        gamePanel.setButtonsDelay(90);
        gamePanel.setBackgroundColor1(new Color(30, 50, 50));
        gamePanel.setBackgroundColor2(new Color(80, 50, 50));
        gamePanel.setBorderColor(new Color(100,255,0));
        gamePanel.setSnakeBodyColor(new Color(0, 150, 250));
        gamePanel.setAppleImage(new ImageIcon("images/default_mode/apple_image.png").getImage());
        gamePanel.setSnakeHeadImageR(new ImageIcon("images/default_mode/default_R.png").getImage());
        gamePanel.setSnakeHeadImageL(new ImageIcon("images/default_mode/default_L.png").getImage());
        gamePanel.setSnakeHeadImageU(new ImageIcon("images/default_mode/default_U.png").getImage());
        gamePanel.setSnakeHeadImageD(new ImageIcon("images/default_mode/default_D.png").getImage());
        gamePanel.setLockImage(new ImageIcon("images/invisible_mode/unlock.png").getImage());
    }

    public void modeImageSetter(){
        gamePanel.modeImage = gamePanel.drawnModeNumber == 0 ? new ImageIcon("images/quick_mode/quick_mode_image.png").getImage()
                : gamePanel.drawnModeNumber == 1 ? new ImageIcon("images/invisible_mode/invisible_mode_image.png").getImage()
                : gamePanel.drawnModeNumber == 2 ? new ImageIcon("images/immortal_mode/immortal_mode_image.png").getImage()
                : new ImageIcon("").getImage();
    }

    public void modesBooleanSetter(){
        gamePanel.setDefaultMode(false);
        gamePanel.setQuickMode(false);
        gamePanel.setImmortalMode(false);
        gamePanel.setInvisibleWallMode(false);
    }

    public void invisibleModeCollisionWall() {
        if (gamePanel.snake_x[0] == gamePanel.getPANEL_WIDTH()+gamePanel.getGAME_PANEL_X()) {
            gamePanel.snake_x[0] = gamePanel.getGAME_PANEL_X();
        } else if (gamePanel.snake_x[0] == -gamePanel.getFRAME_SIZE()+gamePanel.getGAME_PANEL_X()) {
            gamePanel.snake_x[0] = gamePanel.getPANEL_WIDTH()+gamePanel.getGAME_PANEL_X()-gamePanel.getFRAME_SIZE();
        } else if (gamePanel.snake_y[0] == gamePanel.getPANEL_HEIGHT()+gamePanel.getGAME_PANEL_Y()) {
            gamePanel.snake_y[0] = gamePanel.getGAME_PANEL_Y();
        } else if (gamePanel.snake_y[0] == -gamePanel.getFRAME_SIZE()+gamePanel.getGAME_PANEL_Y()) {
            gamePanel.snake_y[0] = gamePanel.getPANEL_HEIGHT()+gamePanel.getGAME_PANEL_Y()-gamePanel.getFRAME_SIZE();
        }
    }

    public void defaultModeCollisionWall(){
        if (gamePanel.snake_x[0] == gamePanel.getPANEL_WIDTH() + gamePanel.getGAME_PANEL_X()) {
            gamePanel.direction = 'X';
            gamePanel.health--;
            gamePanel.snake_x[0] -= gamePanel.getFRAME_SIZE();
            System.out.println("Kolizja X");
        } else if (gamePanel.snake_x[0] == -gamePanel.getFRAME_SIZE()+gamePanel.getGAME_PANEL_X()) {
            gamePanel.direction = 'X';
            gamePanel.health--;
            gamePanel.snake_x[0] += gamePanel.getFRAME_SIZE();
            System.out.println("Kolizja X");
        } else if (gamePanel.snake_y[0] == gamePanel.getPANEL_HEIGHT()+gamePanel.getGAME_PANEL_Y()) {
            gamePanel.direction = 'X';
            gamePanel.health--;
            gamePanel.snake_y[0] -= gamePanel.getFRAME_SIZE();
            System.out.println("Kolizja Y");
        } else if (gamePanel.snake_y[0] == -gamePanel.getFRAME_SIZE()+gamePanel.getGAME_PANEL_Y()) {
            gamePanel.direction = 'X';
            gamePanel.health--;
            gamePanel.snake_y[0] += gamePanel.getFRAME_SIZE();
            System.out.println("Kolizja Y");
        }
    }

    public void quickModeCollisionWall(){
        if (gamePanel.snake_x[0] == gamePanel.getPANEL_WIDTH() + gamePanel.getGAME_PANEL_X()) {
            gamePanel.direction = 'R';
            gamePanel.collision = true;
            System.out.println("Kolizja X");
        } else if (gamePanel.snake_x[0] == -gamePanel.getFRAME_SIZE()+gamePanel.getGAME_PANEL_X()) {
            gamePanel.direction = 'R';
            gamePanel.collision = true;
            System.out.println("Kolizja X");
        } else if (gamePanel.snake_y[0] == gamePanel.getPANEL_HEIGHT()+gamePanel.getGAME_PANEL_Y()) {
            gamePanel.direction = 'R';
            gamePanel.collision = true;
            System.out.println("Kolizja Y");
        } else if (gamePanel.snake_y[0] == -gamePanel.getFRAME_SIZE()+gamePanel.getGAME_PANEL_Y()) {
            gamePanel.direction = 'R';
            gamePanel.collision = true;
            System.out.println("Kolizja Y");
        }
    }

    public void immortalModeCollisionWall() {
        if (gamePanel.snake_x[0] == gamePanel.getPANEL_WIDTH() + gamePanel.getGAME_PANEL_X()) {
            gamePanel.direction = 'X';
            gamePanel.snake_x[0] -= gamePanel.getFRAME_SIZE();
        } else if (gamePanel.snake_x[0] == -gamePanel.getFRAME_SIZE() + gamePanel.getGAME_PANEL_X()) {
            gamePanel.direction = 'X';
            gamePanel.snake_x[0] += gamePanel.getFRAME_SIZE();
        } else if (gamePanel.snake_y[0] == gamePanel.getPANEL_HEIGHT() + gamePanel.getGAME_PANEL_Y()) {
            gamePanel.direction = 'X';
            gamePanel.snake_y[0] -= gamePanel.getFRAME_SIZE();
        } else if (gamePanel.snake_y[0] == -gamePanel.getFRAME_SIZE() + gamePanel.getGAME_PANEL_Y()) {
            gamePanel.direction = 'X';
            gamePanel.snake_y[0] += gamePanel.getFRAME_SIZE();
        }
    }

    public void defaultModeCollisionSnake(){
        for (int i = 1; i < gamePanel.getSNAKE_LENGTH(); i++) {
            if (gamePanel.snake_x[0] == gamePanel.snake_x[i] && gamePanel.snake_y[0] == gamePanel.snake_y[i]) {
                gamePanel.health--;
                System.out.println("Kolizja SNAKE");
                break;
            }
        }
    }

    public void quickModeCollisionSnake() {
        for (int i = 1; i < gamePanel.getSNAKE_LENGTH(); i++) {
            if (gamePanel.snake_x[0] == gamePanel.snake_x[i] && gamePanel.snake_y[0] == gamePanel.snake_y[i]) {
                gamePanel.collision = true;
                System.out.println("Kolizja SNAKE");
                break;
            }
        }
    }

    public void immortalModeCollisionSnake() {
    }

    public void defaultModeCheckEatenObjects(){
        if (gamePanel.apple_x == gamePanel.snake_x[0] && gamePanel.apple_y == gamePanel.snake_y[0]) {
            gamePanel.newApple();
            gamePanel.setSNAKE_LENGTH(gamePanel.getSNAKE_LENGTH()+1);
            gamePanel.SCORE++;
        }
    }

    public void immortalModeCheckEatenObjects(){
        if(gamePanel.apple_x == gamePanel.snake_x[0] && gamePanel.apple_y == gamePanel.snake_y[0]){
            gamePanel.newApple();
            gamePanel.SCORE++;
        }
        else if(gamePanel.apple_x1 == gamePanel.snake_x[0] && gamePanel.apple_y1 == gamePanel.snake_y[0]) {
            gamePanel.newMultipleApple(1);
            gamePanel.SCORE++;
        }
        else if(gamePanel.apple_x2 == gamePanel.snake_x[0] && gamePanel.apple_y2 == gamePanel.snake_y[0]){
            gamePanel.newMultipleApple(2);
            gamePanel.SCORE++;
        }
        else if(gamePanel.apple_x3 == gamePanel.snake_x[0] && gamePanel.apple_y3 == gamePanel.snake_y[0]){
            gamePanel.newMultipleApple(3);
            gamePanel.SCORE++;
        }
    }

    public void modesTimeoutCheck(){
        if(gamePanel.invisibleWallMode){
            if(invisibleModeTime - System.currentTimeMillis() < -20000){
                defaultMode();
            }
        }
        else if(gamePanel.immortalMode){
            if(immortalModeTime - System.currentTimeMillis() < -10000){
                defaultMode();
            }
        }
    }
}
