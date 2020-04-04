package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public final class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 250;
    private final int B_HEIGHT = 550;
    private final int BLOCK = 25;
    private final int TIMERDELAY = 10;
    private final double SPEEDINCREMENT = 1.01;

    private Tetromino current;
    private Tetromino hold = null;

    private final ArrayList<Integer> occupiedX = new ArrayList<>();
    private final ArrayList<Integer> occupiedY = new ArrayList<>();
    private final ArrayList<Image> images = new ArrayList<>();

    private int score;
    private int counter = 0;
    private int delay = 75;
    private int numOfLinesCleared = 0;
    private int stage = 1;

    private double speed;

    private boolean inGame = true;
    private boolean spawned;
    private boolean holdable = false;
    private boolean tetris = true;

    private Timer timer;

    public Board() {
        makePanel();
    }

    public void makePanel() {
        this.addKeyListener(
                new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    if (validLeft()) {
                        current.moveLeft();
                    }
                }
                if (key == KeyEvent.VK_RIGHT) {
                    if (validRight()) {
                        current.moveRight();
                    }
                }
                if (key == KeyEvent.VK_Z) {
                    current.spinLeft();
                    leftSpinCorrection();
                }
                if (key == KeyEvent.VK_UP) {
                    current.spinRight();
                    rightSpinCorrection();
                }
                if (key == KeyEvent.VK_DOWN) {
                    if (validDown()) {
                        for (int j = 0; j < 4; j++) {
                            current.y[j] += BLOCK;
                        }
                    }
                }
                if (key == KeyEvent.VK_SPACE) {
                    hardDrop();
                }
                if (key == KeyEvent.VK_C) {
                    if (holdable) {
                        holdPiece();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH + 6 * BLOCK, B_HEIGHT));
        startGame();
    }

    public void hardDrop() {
        while (spawned) {
            for (int i = 0; i < 4; i++) {
                if (spawned) {
                    for (int j = 0; j < occupiedX.size(); j++) {
                        if (current.x[i] == occupiedX.get(j) && current.y[i] + BLOCK == occupiedY.get(j)) {
                            spawned = false;
                            addCurrent();
                            clear();
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            if (spawned) {
                for (int j = 0; j < 4; j++) {
                    current.y[j] += BLOCK;
                }
            }
        }
    }

    public void holdPiece() {
        if (hold == null) {
            hold = current;
            spawned = false;
            spawnTetromino();
        } else {
            Tetromino temp = current;
            hold.x[hold.getC()] = 100;
            hold.y[hold.getC()] = 25;
            hold.draw();
            current = hold;
            hold = temp;
        }
        holdable = false;
        hold.setOrientation(0);
        hold.x[hold.getC()] = B_WIDTH + 2 * BLOCK;
        hold.y[hold.getC()] = 3 * BLOCK;
        hold.draw();

    }

    public void leftSpinCorrection() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < occupiedX.size(); j++) {
                if (current.x[i] == occupiedX.get(j) && current.y[i] == occupiedY.get(j)) {
                    current.spinRight();
                    break;
                }
            }
            if (current.x[i] < 0) {
                if (current instanceof Line_Piece) {
                    int o = current.getOrientation();
                    if (o == 0) {
                        current.x[current.getC()] += 2 * BLOCK;
                        current.draw();
                    } else {
                        current.x[current.getC()] += BLOCK;
                        current.draw();
                    }
                } else {
                    current.x[current.getC()] += BLOCK;
                    current.draw();
                }
            } else if (current.x[i] > B_WIDTH - BLOCK) {
                if (current instanceof Line_Piece) {
                    int o = current.getOrientation();
                    if (o == 0) {
                        current.x[current.getC()] -= BLOCK;
                        current.draw();
                    } else {
                        current.x[current.getC()] -= 2 * BLOCK;
                        current.draw();
                    }
                } else {
                    current.x[current.getC()] -= BLOCK;
                    current.draw();
                }
            }
        }
    }

    public void rightSpinCorrection() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < occupiedX.size(); j++) {
                if (current.x[i] == occupiedX.get(j) && current.y[i] == occupiedY.get(j)) {
                    current.spinLeft();
                    break;
                }
            }
            if (current.x[i] < 0) {
                if (current instanceof Line_Piece) {
                    int o = current.getOrientation();
                    if (o == 0) {
                        current.x[current.getC()] += BLOCK;
                        current.draw();
                    } else {
                        current.x[current.getC()] += 2 * BLOCK;
                        current.draw();
                    }
                } else {
                    current.x[current.getC()] += 25;
                    current.draw();
                }
            } else if (current.x[i] > B_WIDTH - BLOCK) {
                if (current instanceof Line_Piece) {
                    int o = current.getOrientation();
                    if (o == 0) {
                        current.x[current.getC()] -= 2 * BLOCK;
                        current.draw();
                    } else {
                        current.x[current.getC()] -= BLOCK;
                        current.draw();
                    }
                } else {
                    current.x[current.getC()] -= BLOCK;
                    current.draw();
                }
            }
        }
    }

    public boolean validDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < occupiedX.size(); j++) {
                if (current.x[i] == occupiedX.get(j) && current.y[i] + 25 == occupiedY.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validLeft() {
        for (int i = 0; i < 4; i++) {
            if (current.x[i] == 0) {
                return false;
            }

            for (int j = 0; j < occupiedX.size() - B_WIDTH / BLOCK; j++) {
                if (current.x[i] - BLOCK == occupiedX.get(j + B_WIDTH / BLOCK) && current.y[i] == occupiedY.get(j + B_WIDTH / BLOCK)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validRight() {
        for (int i = 0; i < 4; i++) {
            if (current.x[i] == B_WIDTH - BLOCK) {
                return false;
            }
            for (int j = 0; j < occupiedX.size() - B_WIDTH / BLOCK; j++) {
                if (current.x[i] + BLOCK == occupiedX.get(j + B_WIDTH / BLOCK) && current.y[i] == occupiedY.get(j + B_WIDTH / BLOCK)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    public void drawBoard(Graphics g) {
        g.fillRect(0, 50, B_WIDTH, 5);
        g.fillRect(B_WIDTH, 0, 5, B_HEIGHT);
        g.fillRect(B_WIDTH, 5 * BLOCK, 6 * BLOCK, 5);
        if (hold != null) {
            for (int i = 0; i < 4; i++) {
                g.drawImage(hold.getImage(), hold.x[i], hold.y[i], this);
            }
        }
        for (int i = 0; i < 4; i++) {
            g.drawImage(current.getImage(), current.x[i], current.y[i], this);
        }

        for (int i = 0; i < images.size(); i++) {
            g.drawImage(images.get(i), occupiedX.get(i + B_WIDTH / BLOCK), occupiedY.get(i + B_WIDTH / BLOCK), this);
        }
        String msg = "Score:";
        String msg2 = "" + score;
        String msg3 = "Stage: " + stage;
        String msg4 = "Hold";
        Font f = new Font("Serif", Font.BOLD, 20);
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString(msg, B_WIDTH + 10, B_HEIGHT - 2 * BLOCK);
        g.drawString(msg2, B_WIDTH + 10, B_HEIGHT - BLOCK);
        g.drawString(msg3, B_WIDTH + 10, 8 * BLOCK);
        g.drawString(msg4, B_WIDTH + 10, BLOCK);
        Toolkit.getDefaultToolkit().sync();
        if (!inGame) {
            timer.stop();
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over";
        Font f = new Font("Serif", Font.BOLD, 25);
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString(msg, B_WIDTH + 15, B_HEIGHT / 2 - 20);
    }

    public void startGame() {
        setGround();
        spawned = false;
        spawnTetromino();
        timer = new Timer(TIMERDELAY, this);
        timer.start();
    }

    public void setGround() {
        for (int i = 0; i < B_WIDTH / BLOCK; i++) {
            occupiedX.add(i * BLOCK);
            occupiedY.add(B_HEIGHT);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkGameOver();
        spawnTetromino();
        if (counter % (int) (delay / speed) == 0) {
            move();
        }
        clear();
        updateGame();
        counter++;
        repaint();
    }

    public void updateGame() {
        if (numOfLinesCleared / 10 > stage - 1) {
            stage++;
        }
        speed = (stage - 1) * SPEEDINCREMENT + 1.0;
    }

    public void checkGameOver() {
        for (int i = 0; i < occupiedY.size(); i++) {
            if (occupiedY.get(i) <= 25) {
                inGame = false;
            }
        }
    }

    public void spawnTetromino() {
        if (!spawned) {
            holdable = true;
            int r = (int) (Math.random() * 7);
            if (r == 0) {
                current = new L_Piece();
            } else if (r == 1) {
                current = new J_Piece();
            } else if (r == 2) {
                current = new Z_Piece();
            } else if (r == 3) {
                current = new S_Piece();
            } else if (r == 4) {
                current = new Square_Piece();
            } else if (r == 5) {
                current = new Line_Piece();
            } else {
                current = new T_Piece();
            }
            spawned = true;
        }
    }

    public void move() {
        if (spawned) {
            for (int i = 0; i < 4; i++) {
                if (spawned) {
                    for (int j = 0; j < occupiedX.size(); j++) {
                        if (current.x[i] == occupiedX.get(j) && current.y[i] + BLOCK == occupiedY.get(j)) {
                            spawned = false;
                            addCurrent();
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            if (spawned) {
                for (int j = 0; j < 4; j++) {
                    current.y[j] += BLOCK;
                }
            }
        }
    }

    public void addCurrent() {
        for (int i = 0; i < 4; i++) {
            occupiedX.add(current.x[i]);
            occupiedY.add(current.y[i]);
            images.add(current.getImage());
        }
    }

    public void clear() {
        if (!spawned) {
            ArrayList<Integer> array = new ArrayList<>();
            ArrayList<Integer> clearLevels = new ArrayList<>();
            ArrayList<Integer> indexesOfFall = new ArrayList<>();
            int level;
            int clears = 0;
            boolean validLevel;
            for (int i = 0; i < 4; i++) {
                validLevel = true;
                level = current.y[i];
                for (int j = 10; j < occupiedY.size(); j++) {
                    if (occupiedY.get(j) != null && occupiedY.get(j) == level) {
                        array.add(j);
                    }
                }
                if (array.size() == B_WIDTH / BLOCK) {
                    for (int m = 0; m < clearLevels.size(); m++) {
                        if (level == clearLevels.get(m)) {
                            validLevel = false;
                        }
                    }
                    if (validLevel) {
                        clearLevels.add(level);
                        clears++;
                    }
                    for (int k = 0; k < B_WIDTH / BLOCK; k++) {
                        images.set(array.get(k) - 10, null);
                        occupiedX.set(array.get(k), null);
                        occupiedY.set(array.get(k), null);
                    }
                }
                array.clear();
            }
            for (int i = 0; i < clearLevels.size(); i++) {
                for (int j = 0; j < occupiedY.size(); j++) {
                    if (occupiedY.get(j) != null && occupiedY.get(j) < clearLevels.get(i)) {
                        indexesOfFall.add(j);
                    }
                }
            }
            for (int i = 0; i < indexesOfFall.size(); i++) {
                occupiedY.set(indexesOfFall.get(i), occupiedY.get(indexesOfFall.get(i)) + 25);
            }
            for (int n = images.size() - 1; n >= 0; n--) {
                if (images.get(n) == null) {
                    images.remove(n);
                }
            }
            for (int m = occupiedX.size() - 1; m >= 0; m--) {
                if (occupiedX.get(m) == null) {
                    occupiedX.remove(m);
                    occupiedY.remove(m);
                }
            }
            if (clears == 1) {
                score += 40 * stage;
            } else if (clears == 2) {
                score += 100 * stage;
            } else if (clears == 3) {
                score += 300 * stage;
            } else if (clears == 4) {
                score += 1200 * stage;
                tetris = true;
            }
            numOfLinesCleared += clears;
            clears = 0;
        }
    }
}
