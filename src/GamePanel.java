import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    Modes modes = new Modes(this);
    UI ui = new UI(this);
    private final int PANEL_WIDTH = 600;
    private final int PANEL_HEIGHT = 600;
    private final int GAME_PANEL_X = 30;
    private final int GAME_PANEL_Y = 10;
    private final int FRAME_SIZE = 40;
    private int SNAKE_LENGTH = 1;
    int SCORE = 0;
    int health = 3;
    int apple_x;
    int apple_y;
    int apple_x1;
    int apple_y1;
    int apple_x2;
    int apple_y2;
    int apple_x3;
    int apple_y3;
    int modeObject_x;
    int modeObject_y;
    int[] snake_x = new int[PANEL_WIDTH];
    int[] snake_y = new int[PANEL_HEIGHT];
    int gameSpeed = 130;
    char direction = 'X';
    boolean collision = false;
    int buttonsDelay = 120;
    long currentTime = System.currentTimeMillis();
    int drawnModeNumber;
    boolean defaultMode;
    boolean quickMode;
    boolean immortalMode;
    boolean invisibleWallMode;
    boolean objectCreated;
    Color backgroundColor1;
    Color backgroundColor2;
    Color borderColor;
    Color snakeBodyColor;
    Timer timer;
    Random rd = new Random();
    Image appleImage;
    Image modeImage;
    Image snakeHeadImageR;
    Image snakeHeadImageL;
    Image snakeHeadImageU;
    Image snakeHeadImageD;
    Image snakeHeadOpenImageR;
    Image snakeHeadOpenImageL;
    Image snakeHeadOpenImageU;
    Image snakeHeadOpenImageD;
    Image lockImage;

    GamePanel() {
        KeyboardListener keyboardListener = new KeyboardListener(this);
        this.addKeyListener(keyboardListener);
        this.setFocusable(true);
        this.setBounds(0, 65, PANEL_WIDTH + 60, PANEL_HEIGHT + 60);
        this.setDoubleBuffered(true);
        this.setBackground(Color.darkGray);
        this.setFocusable(true);
        timer = new Timer(gameSpeed, this);
        modes.defaultMode();
        startGame();
    }


    public void startGame() {
        snake_x[0] = 280 + GAME_PANEL_X;
        snake_y[0] = 280 + GAME_PANEL_Y;
        newApple();
        timer.start();
    }

    public void collision() {
        collisionWall();
        collisionSnake();
        if (quickMode && collision) {
            modes.defaultMode();
            collision = false;
            for (int i = 0; i < SNAKE_LENGTH; i++) {
                if (i == 0) {
                    snake_x[0] = GAME_PANEL_X;
                    snake_y[0] = GAME_PANEL_Y + FRAME_SIZE * 6;
                } else {
                    snake_x[i] = snake_x[0]-1;
                    snake_y[i] = snake_y[0]-1;
                }
            }
        }
    }

    public void collisionWall() {
        if (invisibleWallMode) {
            modes.invisibleModeCollisionWall();
        } else if (defaultMode) {
            modes.defaultModeCollisionWall();
        } else if (quickMode) {
            modes.quickModeCollisionWall();
        } else if (immortalMode) {
            modes.immortalModeCollisionWall();
        }
    }

    public void collisionSnake() {
        if (direction != 'X' && !quickMode && !immortalMode) {
            modes.defaultModeCollisionSnake();
        } else if (quickMode) {
            modes.quickModeCollisionSnake();
        } else if (immortalMode){
            modes.immortalModeCollisionSnake();
        }
    }

    public void move() {
        for (int i = SNAKE_LENGTH; i > 0; i--) {
            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];
        }
        switch (direction) {
            case 'R' -> snake_x[0] += FRAME_SIZE;
            case 'L' -> snake_x[0] -= FRAME_SIZE;
            case 'U' -> snake_y[0] -= FRAME_SIZE;
            case 'D' -> snake_y[0] += FRAME_SIZE;
            case 'X' -> {
            }
        }
    }

    public void newModeObject() {
        rd = new Random();
        boolean collision = true;
        while (collision) {
            modeObject_x = (rd.nextInt((PANEL_WIDTH / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_X;
            modeObject_y = (rd.nextInt((PANEL_HEIGHT / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_Y;
            for (int i = 0; i < SNAKE_LENGTH; i++) {
                if ((modeObject_x == snake_x[i] && modeObject_y == snake_y[i]) || (modeObject_x == apple_x && modeObject_y == apple_y)) {
                    collision = true;
                    break;
                } else {
                    collision = false;
                }
            }
        }
    }

    public void newApple() {
        boolean collision = true;
        while (collision) {
            apple_x = (rd.nextInt((PANEL_WIDTH / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_X;
            apple_y = (rd.nextInt((PANEL_HEIGHT / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_Y;
            for (int i = 0; i < SNAKE_LENGTH; i++) {
                if (apple_x == snake_x[i] && apple_y == snake_y[i]) {
                    collision = true;
                    break;
                } else {
                    collision = false;
                }
            }
        }
    }

    public void newMultipleApple(int x) {
        boolean collision = true;
        while (collision) {
            if (x == 1) {
                apple_x1 = (rd.nextInt((PANEL_WIDTH / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_X;
                apple_y1 = (rd.nextInt((PANEL_WIDTH / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_Y;
                for (int i = 0; i < SNAKE_LENGTH; i++) {
                    if (apple_x1 == snake_x[i] && apple_y1 == snake_y[i]) {
                        collision = true;
                        break;
                    }
                    else{
                        collision = false;
                    }
                }
            } else if (x == 2) {
                apple_x2 = (rd.nextInt((PANEL_WIDTH / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_X;
                apple_y2 = (rd.nextInt((PANEL_HEIGHT / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_Y;
                for (int i = 0; i < SNAKE_LENGTH; i++) {
                    if (apple_x2 == snake_x[i] && apple_y2 == snake_y[i]) {
                        collision = true;
                        break;
                    }
                    else{
                        collision = false;
                    }
                }
            } else if (x == 3) {
                apple_x3 = (rd.nextInt((PANEL_WIDTH / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_X;
                apple_y3 = (rd.nextInt((PANEL_HEIGHT / FRAME_SIZE)) * FRAME_SIZE) + GAME_PANEL_Y;
                for (int i = 0; i < SNAKE_LENGTH; i++) {
                    if (apple_x3 == snake_x[i] && apple_y3 == snake_y[i]) {
                        collision = true;
                        break;
                    }
                    else{
                        collision = false;
                    }
                }
            }
        }
    }


    public void checkEatenObjects() {
        if (defaultMode || quickMode || invisibleWallMode) {
            modes.defaultModeCheckEatenObjects();
        }
        else if(immortalMode){
            modes.immortalModeCheckEatenObjects();
        }
        if (modeObject_x == snake_x[0] && modeObject_y == snake_y[0]) {
            if (drawnModeNumber == 0) {
                modes.quickMode();
            } else if (drawnModeNumber == 1) {
                modes.invisibleWallMode();
            } else if (drawnModeNumber == 2){
                modes.immortalMode();
            }
            SCORE += 2;
            resetObject();
        }
    }


    public Image snakeHeadDirection() {
        int x = snake_x[0] - apple_x;
        int y = snake_y[0] - apple_y;
        switch (direction) {
            case 'R' -> {
                if (defaultMode || invisibleWallMode || quickMode) {
                    if ((x > -81 && x < 81) && (y > -81 && y < 81)) {
                        return snakeHeadOpenImageR;
                    }
                }
                return snakeHeadImageR;
            }
            case 'L' -> {
                if (defaultMode || invisibleWallMode || quickMode) {
                    if ((x > -81 && x < 81) && (y > -81 && y < 81)) {
                        return snakeHeadOpenImageL;
                    }
                }
                return snakeHeadImageL;
            }
            case 'U' -> {
                if (defaultMode || invisibleWallMode || quickMode) {
                    if ((x > -81 && x < 81) && (y > -81 && y < 81)) {
                        return snakeHeadOpenImageU;
                    }
                }
                return snakeHeadImageU;
            }
            case 'D' -> {
                if (defaultMode || invisibleWallMode || quickMode) {
                    if ((x > -81 && x < 81) && (y > -81 && y < 81)) {
                        return snakeHeadOpenImageD;
                    }
                }
                return snakeHeadImageD;
            }
        }
        return snakeHeadImageR;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction != 'X') {
            move();
            collision();
            checkEatenObjects();
        }
        modes.modesTimeoutCheck();
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ui.drawBackground(g2d);
        ui.drawLock(g2d);
        ui.drawSnake(g2d);
        ui.drawApple(g2d);
        if(immortalMode){
            ui.drawApple(g2d);
        }
        if (currentTime - System.currentTimeMillis() < -5000 && currentTime - System.currentTimeMillis() > -10000 && defaultMode) {
            objectDrawing(drawnModeNumber, g2d);
        } else if (currentTime - System.currentTimeMillis() < -10000) {
            resetObject();
        }
    }

    public void objectDrawing(int drawNumber, Graphics2D g2d) {
        if (drawNumber == 0) {
            ui.drawQuickModeObject(g2d);
        } else if (drawNumber == 1) {
            ui.drawInvisibleWallObject(g2d);
        } else if(drawNumber == 2){
            ui.drawImmortalModeObject(g2d);
        }
    }

    public void resetObject() {
        modeObject_x = -1;
        modeObject_y = -1;
        currentTime = System.currentTimeMillis();
        objectCreated = false;
        drawnModeNumber = rd.nextInt(0,3);
    }




    // SETTERS , GETTERS
    public void setSNAKE_LENGTH(int SNAKE_LENGTH) {
        this.SNAKE_LENGTH = SNAKE_LENGTH;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void setButtonsDelay(int buttonsDelay) {
        this.buttonsDelay = buttonsDelay;
    }

    public void setDefaultMode(boolean defaultMode) {
        this.defaultMode = defaultMode;
    }

    public void setQuickMode(boolean quickMode) {
        this.quickMode = quickMode;
    }

    public void setImmortalMode(boolean immortalMode) {
        this.immortalMode = immortalMode;
    }

    public void setInvisibleWallMode(boolean invisibleWallMode) {
        this.invisibleWallMode = invisibleWallMode;
    }

    public void setBackgroundColor1(Color backgroundColor1) {
        this.backgroundColor1 = backgroundColor1;
    }

    public void setBackgroundColor2(Color backgroundColor2) {
        this.backgroundColor2 = backgroundColor2;
    }

    public void setSnakeBodyColor(Color snakeBodyColor) {
        this.snakeBodyColor = snakeBodyColor;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setDrawnModeNumber(int drawnModeNumber) {
        this.drawnModeNumber = drawnModeNumber;
    }

    public void setAppleImage(Image appleImage) {
        this.appleImage = appleImage;
    }

    public void setSnakeHeadImageR(Image snakeHeadImageR) {
        this.snakeHeadImageR = snakeHeadImageR;
    }

    public void setSnakeHeadImageL(Image snakeHeadImageL) {
        this.snakeHeadImageL = snakeHeadImageL;
    }

    public void setSnakeHeadImageU(Image snakeHeadImageU) {
        this.snakeHeadImageU = snakeHeadImageU;
    }

    public void setSnakeHeadImageD(Image snakeHeadImageD) {
        this.snakeHeadImageD = snakeHeadImageD;
    }

    public int getPANEL_WIDTH() {
        return PANEL_WIDTH;
    }

    public int getPANEL_HEIGHT() {
        return PANEL_HEIGHT;
    }

    public int getFRAME_SIZE() {
        return FRAME_SIZE;
    }

    public int getSNAKE_LENGTH() {
        return SNAKE_LENGTH;
    }

    public void setLockImage(Image lockImage) {
        this.lockImage = lockImage;
    }

    public int getGAME_PANEL_X() {
        return GAME_PANEL_X;
    }

    public int getGAME_PANEL_Y() {
        return GAME_PANEL_Y;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setSnakeHeadOpenImageD(Image snakeHeadOpenImageD) {
        this.snakeHeadOpenImageD = snakeHeadOpenImageD;
    }

    public void setSnakeHeadOpenImageR(Image snakeHeadOpenImageR) {
        this.snakeHeadOpenImageR = snakeHeadOpenImageR;
    }

    public void setSnakeHeadOpenImageL(Image snakeHeadOpenImageL) {
        this.snakeHeadOpenImageL = snakeHeadOpenImageL;
    }

    public void setSnakeHeadOpenImageU(Image snakeHeadOpenImageU) {
        this.snakeHeadOpenImageU = snakeHeadOpenImageU;
    }
}


