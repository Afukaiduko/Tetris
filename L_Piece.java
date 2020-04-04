package tetris;

import javax.swing.ImageIcon;

public class L_Piece extends Tetromino {

    L_Piece() {
        c = 1;
        x[c] = 100;
        y[c] = 25;
        draw();
        ImageIcon iiL = new ImageIcon(getClass().getResource("L.png"));
        I = iiL.getImage();
    }

    @Override
    public void draw() {
        if (orientation == 0) {
            x[0] = x[c] - 25;
            x[2] = x[c] + 25;
            x[3] = x[c] + 25;
            y[0] = y[c];
            y[2] = y[c];
            y[3] = y[c] - 25;
        } else if (orientation == 1) {
            x[0] = x[c];
            x[2] = x[c];
            x[3] = x[c] + 25;
            y[0] = y[c] - 25;
            y[2] = y[c] + 25;
            y[3] = y[c] + 25;
        } else if (orientation == 2) {
            x[0] = x[c] + 25;
            x[2] = x[c] - 25;
            x[3] = x[c] - 25;
            y[0] = y[c];
            y[2] = y[c];
            y[3] = y[c] + 25;
        } else {
            x[0] = x[c];
            x[2] = x[c];
            x[3] = x[c] - 25;
            y[0] = y[c] + 25;
            y[2] = y[c] - 25;
            y[3] = y[c] - 25;
        }
    }
}
