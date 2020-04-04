package tetris;

import javax.swing.ImageIcon;

public class J_Piece extends Tetromino {

    J_Piece() {
        c = 2;
        x[c] = 100;
        y[c] = 25;
        draw();
        ImageIcon iiJ = new ImageIcon(getClass().getResource("J.png"));
        I = iiJ.getImage();
    }

    @Override
    public void draw() {
        if (orientation == 0) {
            x[0] = x[c] - 25;
            x[1] = x[c] - 25;
            x[3] = x[c] + 25;
            y[0] = y[c] - 25;
            y[1] = y[c];
            y[3] = y[c];
        } else if (orientation == 1) {
            x[0] = x[c] + 25;
            x[1] = x[c];
            x[3] = x[c];
            y[0] = y[c] - 25;
            y[1] = y[c] - 25;
            y[3] = y[c] + 25;
        } else if (orientation == 2) {
            x[0] = x[c] + 25;
            x[1] = x[c] + 25;
            x[3] = x[c] - 25;
            y[0] = y[c] + 25;
            y[1] = y[c];
            y[3] = y[c];
        } else {
            x[0] = x[c] - 25;
            x[1] = x[c];
            x[3] = x[c];
            y[0] = y[c] + 25;
            y[1] = y[c] + 25;
            y[3] = y[c] - 25;
        }
    }
}
