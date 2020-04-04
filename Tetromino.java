package tetris;

import java.awt.Image;

public abstract class Tetromino {

    public int[] x = new int[4];
    public int[] y = new int[4];
    public int orientation = 0;
    public Image I;
    public int c;

    Tetromino() {

    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int n) {
        orientation = n;
    }

    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            x[i] -= 25;
        }
    }

    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            x[i] += 25;
        }
    }

    public void spinLeft() {
        orientation--;
        if (orientation < 0) {
            orientation = 3;
        }
        draw();
    }

    public void spinRight() {
        orientation++;
        if (orientation > 3) {
            orientation = 0;
        }
        draw();
    }

    public abstract void draw();

    public Image getImage() {
        return I;
    }

    public int getC() {
        return c;
    }
}
