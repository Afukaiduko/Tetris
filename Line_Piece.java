package tetris;

import javax.swing.ImageIcon;

public class Line_Piece extends Tetromino {

    Line_Piece() {
        c = 1;
        x[c] = 100;
        y[c] = 25;
        draw();
        ImageIcon iiline = new ImageIcon(getClass().getResource("Line.png"));
        I = iiline.getImage();
    }

    @Override
    public void draw() {
        if (orientation == 0) {
            x[0] = x[c] - 25;
            x[2] = x[c] + 25;
            x[3] = x[c] + 50;
            y[0] = y[c];
            y[2] = y[c];
            y[3] = y[c];
        } else if (orientation == 1) {
            x[0] = x[c];
            x[2] = x[c];
            x[3] = x[c];
            y[0] = y[c] - 25;
            y[2] = y[c] + 25;
            y[3] = y[c] + 50;
        } else if (orientation == 2) {
            x[0] = x[c] + 25;
            x[2] = x[c] - 25;
            x[3] = x[c] - 50;
            y[0] = y[c];
            y[2] = y[c];
            y[3] = y[c];
        } else {
            x[0] = x[c];
            x[2] = x[c];
            x[3] = x[c];
            y[0] = y[c] + 25;
            y[2] = y[c] - 25;
            y[3] = y[c] - 50;
        }
    }

    @Override
    public void spinLeft() {
        if (orientation == 0) {
            y[c] += 25;
        } else if (orientation == 1) {
            x[c] -= 25;
        } else if (orientation == 2) {
            y[c] -= 25;
        } else {
            x[c] += 25;
        }
        orientation--;

        if (orientation < 0) {
            orientation = 3;
        }
        draw();
    }

    @Override
    public void spinRight() {
        if (orientation == 0) {
            x[c] += 25;
        } else if (orientation == 1) {
            y[c] += 25;
        } else if (orientation == 2) {
            x[c] -= 25;
        } else {
            y[c] -= 25;
        }
        orientation++;
        if (orientation > 3) {
            orientation = 0;
        }
        draw();
    }
}
