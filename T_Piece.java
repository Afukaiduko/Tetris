package tetris;

import javax.swing.ImageIcon;

public class T_Piece extends Tetromino {

    T_Piece() {
        c = 2;
        x[c] = 100;
        y[c] = 25;
        draw();
        ImageIcon iiT = new ImageIcon(getClass().getResource("T.png"));
        I = iiT.getImage();
    }

    public void draw() {
        if (orientation == 0) {
            x[0] = x[c] - 25;
            x[1] = x[c];
            x[3] = x[c] + 25;
            y[0] = y[c];
            y[1] = y[c] - 25;
            y[3] = y[c];
        } else if (orientation == 1) {
            x[0] = x[c];
            x[1] = x[c] + 25;
            x[3] = x[c];
            y[0] = y[c] - 25;
            y[1] = y[c];
            y[3] = y[c] + 25;
        } else if (orientation == 2) {
            x[0] = x[c] + 25;
            x[1] = x[c];
            x[3] = x[c] - 25;
            y[0] = y[c];
            y[1] = y[c] + 25;
            y[3] = y[c];
        } else {
            x[0] = x[c];
            x[1] = x[c] - 25;
            x[3] = x[c];
            y[0] = y[c] + 25;
            y[1] = y[c];
            y[3] = y[c] - 25;
        }
    }
}
